package com.jotaveerref.Videolandia.DTO;

import com.jotaveerref.Videolandia.domain.Video;
import jakarta.validation.constraints.NotNull;

public record DadosRegistroVideo(
        @NotNull
        String titulo,
        @NotNull
        String descricao,
        @NotNull
        String url) {

        public DadosRegistroVideo(Video video){
                this(video.getTitulo(), video.getDescricao(), video.getUrl());
        }
}
