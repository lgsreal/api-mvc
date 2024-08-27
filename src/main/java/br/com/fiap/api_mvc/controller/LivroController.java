package br.com.fiap.api_mvc.controller;

import br.com.fiap.api_mvc.dto.LivroRequest;
import br.com.fiap.api_mvc.dto.LivroResponse;
import br.com.fiap.api_mvc.model.Livro;
import br.com.fiap.api_mvc.repository.LivroRepository;
import br.com.fiap.api_mvc.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
// localhost:8080/livros
@RequestMapping(value = "/livros")
public class LivroController {
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private LivroService livroService;

    // CRUD
    @PostMapping
    public ResponseEntity<LivroResponse> create(@RequestBody LivroRequest livroRequest) {
        Livro livroConvertido = livroService.requestToLivro(livroRequest);
        Livro livroPersistido = livroRepository.save(livroConvertido);
        LivroResponse livroResponse = livroService.livroToResponse(livroPersistido);
        return new ResponseEntity<>(livroResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LivroResponse>> read() {
        List<Livro> listaLivros = livroRepository.findAll();
        if (listaLivros.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<LivroResponse> listaLivrosResponse = new ArrayList<>();
        for (Livro livro : listaLivros) {
            LivroResponse livroResponse = livroService.livroToResponse(livro);
            livroResponse.setLink(
                    linkTo(
                            methodOn(LivroController.class)
                                    .read(livro.getId())).withSelfRel());
            listaLivrosResponse.add(livroResponse);
        }
        return new ResponseEntity<>(listaLivrosResponse, HttpStatus.OK);
    }
    // /livros?id=1
    @GetMapping("/{id}")
    public ResponseEntity<LivroResponse> read(@PathVariable Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            LivroResponse livroResponse = livroService.livroToResponse(livro.get());
            return new ResponseEntity<>(livroResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // /livros/1
    @PutMapping("/{id}")
    public ResponseEntity<LivroResponse> update(@PathVariable Long id, @RequestBody LivroRequest livroRequest) {
        Optional<Livro> livroPersistido = livroRepository.findById(id);
        if (livroPersistido.isPresent()) {
            Livro livro = livroService.requestToLivro(livroRequest);
            livro.setId(id);
            Livro livroAtualizado = livroRepository.save(livro);
            LivroResponse livroResponse = livroService.livroToResponse(livroAtualizado);
            return new ResponseEntity<>(livroResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            livroRepository.delete(livro.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
