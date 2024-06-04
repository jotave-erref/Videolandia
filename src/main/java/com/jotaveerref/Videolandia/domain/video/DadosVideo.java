package com.jotaveerref.Videolandia.domain.video;

import com.jotaveerref.Videolandia.domain.video.Video;

public record DadosVideo(
        Long id,
        String titulo,
        String descricao,
        String url,
        boolean ativo) {
    public DadosVideo(Video video){
        this(video.getId(), video.getTitulo(), video.getDescricao(), video.getUrl(), video.isAtivo());
    }
}
