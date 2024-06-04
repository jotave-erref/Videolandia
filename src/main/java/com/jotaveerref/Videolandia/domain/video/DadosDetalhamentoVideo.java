package com.jotaveerref.Videolandia.domain.video;

public record DadosDetalhamentoVideo(Long id, String titulo, String descricao, String url, Long categoriaID) {

    public DadosDetalhamentoVideo(Video video){
        this(video.getId(), video.getTitulo(), video.getDescricao(), video.getUrl(), video.getCategoria().getId());
    }
}
