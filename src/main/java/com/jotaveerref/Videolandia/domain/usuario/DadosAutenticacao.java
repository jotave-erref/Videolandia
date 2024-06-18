package com.jotaveerref.Videolandia.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAutenticacao(
        @NotNull(message = "Usuário ou senha inválido")
        String usuario,
        @NotNull(message = "Usuário ou senha inválido")
        String senha) {
}
