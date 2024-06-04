package com.jotaveerref.Videolandia.domain.video;

import com.jotaveerref.Videolandia.domain.video.Video;
import jakarta.validation.constraints.NotBlank;
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
