package tarefas.dao;

import conexao.ConnectionFactory;
import tarefas.modelo.Receita;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReceitaDao {
    private final Connection connection;

    public ReceitaDao() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void altera(Receita receita) {
        try {
            CallableStatement stmt = null;
            String sql = "{call alterarReceita(?,?,?,?)}";
            stmt = connection.prepareCall(sql);

            stmt.setString(1, receita.getDescricao());

            if (receita.getData() != null) {
                stmt.setDate(2, new Date(receita.getData().getTimeInMillis()));
            } else {
                stmt.setDate(2, null);
            }
            stmt.setTime(3, receita.getHora());
            stmt.setLong(4, receita.getConsultaCodConsulta());

            stmt.execute();

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Receita buscaPorId(Receita receita) {
        CallableStatement stmt = null;
        try {
            String sql = "{call buscarReceita(?,?,?,?)}";
            stmt = connection.prepareCall(sql);

            stmt.setLong(1, receita.getConsultaCodConsulta());

            stmt.execute();

            String descricao = stmt.getString(2);
            Date datal = stmt.getDate(3);
            Calendar data = null;
            if (datal != null) {
                data = Calendar.getInstance();
                data.setTime(datal);
            }

            String hora = stmt.getString(4);

            receita.setDescricao(descricao);
            receita.setData(data);
            receita.setHora(Time.valueOf(hora));

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error while calling buscarReceita procedure", e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error closing statement", e);
            }
        }

        return receita;
    }
    public void adiciona(Receita receita) {
        try {
            CallableStatement stmt = null;
            String sql = "{call insertReceita(?,?,?)}";
            stmt = connection.prepareCall(sql);
            stmt.setString(1, receita.getDescricao());
            LocalDateTime currentDateTime = LocalDateTime.now();

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            String formattedDate = currentDateTime.format(dateFormatter);
            String formattedTime = currentDateTime.format(timeFormatter);
            stmt.setString(2, formattedDate);
            stmt.setString(3, formattedTime);

            stmt.execute();

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Receita receita) {
        try {
            CallableStatement stmt = null;
            String sql = "{call removeReceita(?)}";
            stmt = connection.prepareCall(sql);

            stmt.setInt(1, receita.getConsultaCodConsulta());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Receita> lista() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Receita> receitas = new ArrayList<>();
        try {
            // Prepare the stored procedure call
            String call = "{CALL ListTableContents(?)}";
            preparedStatement = connection.prepareStatement(call);
            preparedStatement.setString(1, "receita");

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                Receita receita = new Receita();
                int id = resultSet.getInt("consultaCodConsulta");
                java.sql.Date date = resultSet.getDate("data");
                java.sql.Time time = resultSet.getTime("hora");
                String description = resultSet.getString("descricao");

                receita.setDescricao(description);
                if (date != null) {
                    Calendar dataFinalizacaoCal = Calendar.getInstance();
                    dataFinalizacaoCal.setTime(date);
                    receita.setData(dataFinalizacaoCal);
                }
                receita.setHora(time);
                receita.setConsultaCodConsulta(id);

                receitas.add(receita);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receitas;
    }
}
