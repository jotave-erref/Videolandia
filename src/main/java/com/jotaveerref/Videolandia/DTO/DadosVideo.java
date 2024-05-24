package com.jotaveerref.Videolandia.DTO;

import com.jotaveerref.Videolandia.domain.Video;

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
