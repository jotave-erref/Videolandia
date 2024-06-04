package com.jotaveerref.Videolandia.domain.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaCategoria(
        @NotNull
        Long id,

        String titulo,

        Cor cor) {
}
