package br.com.fiap.api_mvc.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "tb_prateleira")
public class Prateleira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @ManyToMany
    @JoinTable(name = "tb_livro_prateleira",
            joinColumns = @JoinColumn(name = "id_prateleira", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_livro", referencedColumnName = "id")
    )
    private Collection<Livro> livros;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Collection<Livro> getLivros() {
        return livros;
    }

    public void setLivros(Collection<Livro> livros) {
        this.livros = livros;
    }
}
