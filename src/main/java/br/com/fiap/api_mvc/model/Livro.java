package br.com.fiap.api_mvc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
@Entity
@Table(name = "tb_livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @Column(name = "preco")
    @DecimalMin(value = "0.99", message = "O valor mínimo deve ser 0.99")
    private BigDecimal preco;
    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "A categoria do livro é obrigatória")
    private Categoria categoria;
    @Column(name = "autoria")
    @Size(min = 4, message = "O nome do autor deve ter no mínimo 3 caracteres")
    @NotBlank(message = "O nome do(a) autor(a) não deve estar em branco")
    private String autoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
