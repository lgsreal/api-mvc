package br.com.fiap.api_mvc.model;

public enum Categoria {
    ROMANCE("Romance"),
    FICCAO("Ficção"),
    FANTASIA("Fantasia"),
    BIOGRAFIA("Biografia");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
