package com.jotaveerref.Videolandia.domain.video;

import com.jotaveerref.Videolandia.domain.categoria.Categoria;
import com.jotaveerref.Videolandia.domain.video.Video;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosRegistroVideo(
        @NotBlank
        String titulo,
        @NotBlank
        String descricao,
        @NotBlank
        String url,
        @NotNull
        Long categoriaId
) {

        public DadosRegistroVideo(Video video){
                this(video.getTitulo(), video.getDescricao(), video.getUrl(), video.getCategoria().getId());
        }


}
