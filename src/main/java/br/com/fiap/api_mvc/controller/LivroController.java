package br.com.fiap.api_mvc.controller;

import br.com.fiap.api_mvc.model.Livro;
import br.com.fiap.api_mvc.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/livros")
public class LivroController {
    @Autowired
    private LivroRepository livroRepository;

    // CRUD
    @PostMapping
    public ResponseEntity<Livro> create(@RequestBody Livro livro) {
        Livro livroPersistido = livroRepository.save(livro);
        return new ResponseEntity<>(livroPersistido, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> read() {
        List<Livro> listaLivros = livroRepository.findAll();
        if (listaLivros.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaLivros, HttpStatus.OK);
    }
}
