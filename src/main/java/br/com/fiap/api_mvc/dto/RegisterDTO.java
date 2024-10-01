package br.com.fiap.api_mvc.dto;

import br.com.fiap.api_mvc.model.UserRole;

public record RegisterDTO(String login, String senha, UserRole role) {}
