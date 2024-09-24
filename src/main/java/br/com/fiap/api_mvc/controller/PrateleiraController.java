package br.com.fiap.api_mvc.controller;

import br.com.fiap.api_mvc.dto.LivroPrateleiraRequest;
import br.com.fiap.api_mvc.dto.PrateleiraRequest;
import br.com.fiap.api_mvc.model.Livro;
import br.com.fiap.api_mvc.model.Prateleira;
import br.com.fiap.api_mvc.repository.LivroRepository;
import br.com.fiap.api_mvc.repository.PrateleiraRepository;
import br.com.fiap.api_mvc.service.PrateleiraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prateleiras")
public class PrateleiraController {
    @Autowired
    private PrateleiraRepository prateleiraRepository;
    @Autowired
    private PrateleiraService prateleiraService;
    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    public ResponseEntity<Prateleira> createPrateleira(@Valid @RequestBody PrateleiraRequest prateleiraRequest) {
        Prateleira prateleira = prateleiraRepository
                .save(prateleiraService.requestToPrateleira(prateleiraRequest));
        if (prateleira.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(prateleira, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Prateleira>> readPrateleiras() {
        List<Prateleira> listaPrateleiras = prateleiraRepository.findAll();
        if (listaPrateleiras.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaPrateleiras, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> addLivroPrateleira(
            @Valid @RequestBody LivroPrateleiraRequest livroPrateleiraRequest
    ) {
        Optional<Prateleira> prateleira = prateleiraRepository
                .findById(livroPrateleiraRequest.getIdPrateleira());
        Optional<Livro> livro = livroRepository
                .findById(livroPrateleiraRequest.getIdLivro());
        if (prateleira.isEmpty() || livro.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        prateleira.get().getLivros().add(livro.get());
        prateleiraRepository.save(prateleira.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
