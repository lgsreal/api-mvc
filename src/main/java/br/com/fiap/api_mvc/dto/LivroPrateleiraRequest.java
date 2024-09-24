package br.com.fiap.api_mvc.dto;

import jakarta.validation.constraints.NotNull;

public class LivroPrateleiraRequest {
    @NotNull(message = "O id da prateleira é obrigatório")
    private Long idPrateleira;
    @NotNull(message = "O id do livro é obrigatório")
    private Long idLivro;

    public Long getIdPrateleira() {
        return idPrateleira;
    }

    public void setIdPrateleira(Long idPrateleira) {
        this.idPrateleira = idPrateleira;
    }

    public Long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
    }
}
