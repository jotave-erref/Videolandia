package com.jotaveerref.Videolandia.domain.video;

import com.jotaveerref.Videolandia.domain.categoria.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Video")
@Table(name = "videos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Video(DadosRegistroVideo dados) {
        this.ativo = true;
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.url = dados.url();
    }

    public void atualizaVideo(DadosAtualizaVideo dados){
        if(dados.titulo() != null){
            this.titulo = dados.titulo();
        }

        if(dados.descricao() != null){
            this.descricao = dados.descricao();
        }

        if(dados.url() != null){
            this.url = dados.url();
        }
    }

    public void setCategoria(Categoria categoria){
        this.categoria = categoria;
        categoria.getVideo().add(this);
    }

    public void exclusaoLogica(){
        this.ativo = false;
    }

    public void ativaVideo() {
        this.ativo = true;
    }
}
