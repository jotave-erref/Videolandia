package com.jotaveerref.Videolandia.DTO;

import com.jotaveerref.Videolandia.domain.Video;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaVideo(
        @NotNull
        Long id,
        String titulo,
        String descricao,
        String url
) {
        public DadosAtualizaVideo(Video video){
                this(video.getId(), video.getTitulo(), video.getDescricao(), video.getUrl());
        }
}
