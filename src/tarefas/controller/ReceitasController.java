package tarefas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import tarefas.dao.ReceitaDao;
import tarefas.modelo.Receita;

import javax.validation.Valid;

@Controller
public class ReceitasController {
    private final ReceitaDao dao = new ReceitaDao();

    @RequestMapping("novaReceita")
    public String form() {
        return "receita/formulario";
    }

    @RequestMapping("adicionaReceita")
    public String adiciona(@Valid Receita receita, BindingResult result, Model model) {
        if (result.hasFieldErrors("descricao")) {
            return "receita/formulario";
        }
        dao.adiciona(receita);
        model.addAttribute("nome", "receita");
        return "receita/adicionada";
    }

    @RequestMapping("listaReceita")
    public String lista(Model model) {
        model.addAttribute("receitas", dao.lista());
        return "receita/lista";
    }
    @RequestMapping("removeReceita")
    public String remove(Receita receita)  {
        dao.remove(receita);
        return "redirect:listaReceita";
    }

    @RequestMapping("mostraReceita")
    public String mostra(Receita receita, Model model) {
        model.addAttribute("receita", dao.buscaPorId(receita));
        return "receita/mostra";
    }

    @RequestMapping("alteraReceita")
    public String altera(Receita receita) {
        dao.altera(receita);
        return "redirect:listaReceita";
    }

    @RequestMapping("/ok")
    public String execute() {
        System.out.println("Executando a logica com Spring MVC");
        return "ok";
    }
}
