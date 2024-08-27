package br.com.fiap.api_mvc.model;

public enum Categoria {
    ROMANCE("Categoria Romance"),
    FICCAO("Categoria Ficção"),
    FANTASIA("Categoria Fantasia"),
    BIOGRAFIA("Categoria Biografia");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
