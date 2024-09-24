package br.com.fiap.api_mvc.service;

import br.com.fiap.api_mvc.dto.PrateleiraRequest;
import br.com.fiap.api_mvc.model.Prateleira;
import org.springframework.stereotype.Service;

@Service
public class PrateleiraService {
    public Prateleira requestToPrateleira(PrateleiraRequest prateleiraRequest) {
        Prateleira prateleira = new Prateleira();
        prateleira.setNome(prateleiraRequest.getNome());
        prateleira.setDescricao(prateleiraRequest.getDescricao());
        return prateleira;
    }
}
