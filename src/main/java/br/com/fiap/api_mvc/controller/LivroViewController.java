package br.com.fiap.api_mvc.controller;

import br.com.fiap.api_mvc.dto.LivroRequest;
import br.com.fiap.api_mvc.dto.LivroResponse;
import br.com.fiap.api_mvc.model.Livro;
import br.com.fiap.api_mvc.repository.LivroRepository;
import br.com.fiap.api_mvc.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LivroViewController {
    @Autowired
    private LivroService livroService;
    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/listaLivros")
    public ModelAndView listaLivros() {
        List<Livro> listaLivros = livroRepository.findAll();
        List<LivroResponse> listaLivrosResponse = new ArrayList<>();
        for (Livro livro : listaLivros) {
            listaLivrosResponse.add(livroService.livroToResponse(livro));
        }
        ModelAndView mv = new ModelAndView("livros");
        mv.addObject("listaLivros", listaLivrosResponse);
        return mv;
    }

    @GetMapping("/template")
    public String template() {
        return "templateVazio";
    }

    @GetMapping("/listaLivrosTemplate")
    public ModelAndView listaLivrosTemplate() {
        List<Livro> listaLivros = livroRepository.findAll();
        List<LivroResponse> listaLivrosResponse = new ArrayList<>();
        for (Livro livro : listaLivros) {
            listaLivrosResponse.add(livroService.livroToResponse(livro));
        }
        ModelAndView mv = new ModelAndView("template");
        mv.addObject("page", "livros");
        mv.addObject("content", "listaLivros");
        mv.addObject("listaLivros", listaLivrosResponse);
        return mv;
    }

    @GetMapping("/cadastroLivro")
    public ModelAndView cadastroLivro() {
        ModelAndView mv = new ModelAndView("template");
        mv.addObject("page", "livroCadastro");
        mv.addObject("content", "formCadastro");
        mv.addObject("livroRequest", new LivroRequest());
        return mv;
    }

    @RequestMapping(value = "cadastrarLivro", method = RequestMethod.POST)
    public ModelAndView cadastrarLivro(@ModelAttribute LivroRequest livroRequest) {
        Livro livro = livroService.requestToLivro(livroRequest);
        livroRepository.save(livro);
        return listaLivrosTemplate();
    }

}
