package br.com.fiap.api_mvc.controller;

import br.com.fiap.api_mvc.dto.LivroRequest;
import br.com.fiap.api_mvc.dto.LivroResponse;
import br.com.fiap.api_mvc.model.Livro;
import br.com.fiap.api_mvc.repository.LivroRepository;
import br.com.fiap.api_mvc.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @PostMapping("/cadastrarLivro")
    public ModelAndView cadastrarLivro(@Valid @ModelAttribute LivroRequest livroRequest) {
        Livro livro = livroService.requestToLivro(livroRequest);
        livroRepository.save(livro);
        return listaLivrosTemplate();
    }

    @GetMapping("/editaLivro/{id}")
    public ModelAndView editaLivro(@PathVariable Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isEmpty()) {
            return listaLivrosTemplate();
        }
        ModelAndView mv = new ModelAndView("template");
        mv.addObject("page", "livroEdicao");
        mv.addObject("content", "formEdicao");
        mv.addObject("livroRequest", livroService.livroToRequest(livro.get()));
        mv.addObject("idLivro", id);
        return mv;
    }

    @PostMapping("/atualizarLivro/{id}")
    public ModelAndView atualizarLivro(@PathVariable Long id,
                                       @Valid @ModelAttribute LivroRequest livroRequest) {
        if (livroRepository.findById(id).isEmpty()) {
            // adicionar informação erro
            return listaLivrosTemplate();
        }
        Livro livro = livroService.requestToLivro(livroRequest);
        livro.setId(id);
        livroRepository.save(livro);
        return listaLivrosTemplate();
    }

    @GetMapping("/deletarLivro/{id}")
    public ModelAndView deletarLivro(@PathVariable Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isEmpty()) {
            // tratar exceção
            return listaLivrosTemplate();
        }
        livroRepository.delete(livro.get());
        return listaLivrosTemplate();
    }

}
