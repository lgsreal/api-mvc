package br.com.fiap.api_mvc.controller;

import br.com.fiap.api_mvc.dto.LivroResponse;
import br.com.fiap.api_mvc.model.Livro;
import br.com.fiap.api_mvc.repository.LivroRepository;
import br.com.fiap.api_mvc.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
