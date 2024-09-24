package br.com.fiap.api_mvc.dto;

import jakarta.validation.constraints.NotBlank;

public class PrateleiraRequest {
    @NotBlank(message = "A prateleira deve ter um nome")
    private String nome;
    private String descricao;

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
}
