package br.com.fiap.api_mvc.controller;

import br.com.fiap.api_mvc.model.Livro;
import br.com.fiap.api_mvc.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
// localhost:8080/livros
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
    // /livros?id=1
    @GetMapping("/{id}")
    public ResponseEntity<Livro> read(@PathVariable Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            return new ResponseEntity<>(livro.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // /livros/1
    @PutMapping("/{id}")
    public ResponseEntity<Livro> update(@PathVariable Long id, @RequestBody Livro livro) {
        Optional<Livro> livroPersistido = livroRepository.findById(id);
        if (livroPersistido.isPresent()) {
            livro.setId(id);
            return new ResponseEntity<>(livroRepository.save(livro), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Livro> delete(@PathVariable Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            livroRepository.delete(livro.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
