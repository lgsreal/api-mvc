package br.com.fiap.api_mvc.dto;

import br.com.fiap.api_mvc.model.Categoria;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class LivroRequest {
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @DecimalMin(value = "0.99", message = "O valor mínimo deve ser 0.99")
    private BigDecimal preco;
    @NotNull(message = "A categoria do livro é obrigatória")
    private Categoria categoria;
    @Size(min = 4, message = "O nome do autor deve ter no mínimo 3 caracteres")
    @NotBlank(message = "O nome do(a) autor(a) não deve estar em branco")
    private String autoria;
    @Pattern(regexp = "^\\d{13}$", message = "O ISBN deve ter 13 dígitos")
    private String isbn13;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getAutoria() {
        return autoria;
    }

    public void setAutoria(String autoria) {
        this.autoria = autoria;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }
}
