package br.com.fiap.api_mvc.service;

import br.com.fiap.api_mvc.dto.LivroRequest;
import br.com.fiap.api_mvc.dto.LivroResponse;
import br.com.fiap.api_mvc.model.Livro;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    // Métodos Mapper
    public Livro requestToLivro(LivroRequest livroRequest) {
        Livro livro = new Livro();
        livro.setNome(livroRequest.getNome());
        livro.setCategoria(livroRequest.getCategoria());
        livro.setPreco(livroRequest.getPreco());
        livro.setAutoria(livroRequest.getAutoria());
        livro.setIsbn13(livroRequest.getIsbn13());
        return livro;
    }

    public LivroResponse livroToResponse(Livro livro) {
        LivroResponse livroResponse = new LivroResponse();
        livroResponse.setId(livro.getId());
        livroResponse.setNome(livro.getNome());
        livroResponse.setPreco(livro.getPreco());
        livroResponse.setCategoria(livro.getCategoria().getDescricao());
        livroResponse.setAutoria(livro.getAutoria());
        livroResponse.setIsbn13(livro.getIsbn13());
        return livroResponse;
    }

    public LivroRequest livroToRequest(Livro livro) {
        LivroRequest livroRequest = new LivroRequest();
        livroRequest.setNome(livro.getNome());
        livroRequest.setPreco(livro.getPreco());
        livroRequest.setCategoria(livro.getCategoria());
        livroRequest.setAutoria(livro.getAutoria());
        livroRequest.setIsbn13(livro.getIsbn13());
        return livroRequest;
    }
}
