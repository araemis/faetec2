package tarefas.modelo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.sql.Time;
import java.util.Calendar;


public class Receita {
    private int consultaCodConsulta;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Calendar data;
    private Time hora;

    @Size(min = 5, message = "Descricao deve ter pelo menos 5 carateres")
    private String descricao;

    public int getConsultaCodConsulta() {
        return consultaCodConsulta;
    }

    public void setConsultaCodConsulta(int consultaCodConsulta) {
        this.consultaCodConsulta = consultaCodConsulta;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Receita{" + "consultaCodConsulta=" + consultaCodConsulta + ", data=" + data + ", hora=" + hora + ", descricao='" + descricao + '\'' + '}';
    }
}
