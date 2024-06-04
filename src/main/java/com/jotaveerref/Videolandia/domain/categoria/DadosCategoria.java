package com.jotaveerref.Videolandia.domain.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCategoria(
        Long id,
        @NotBlank(message = "Campo obrigatório")
        String titulo,
        @NotNull(message = "Campo obrigatório")
        Cor cor) {

        public DadosCategoria(Categoria categoria){
                this(categoria.getId(), categoria.getTitulo(), categoria.getCor());
        }
}
