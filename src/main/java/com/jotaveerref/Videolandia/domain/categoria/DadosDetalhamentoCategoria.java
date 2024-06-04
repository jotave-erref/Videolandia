package com.jotaveerref.Videolandia.domain.categoria;

public record DadosDetalhamentoCategoria(
        String titulo,
        Cor cor) {
    public DadosDetalhamentoCategoria(Categoria categoria){
        this(categoria.getTitulo(), categoria.getCor());
    }
}
