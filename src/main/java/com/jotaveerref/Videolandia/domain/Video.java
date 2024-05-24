package com.jotaveerref.Videolandia.domain;

import com.jotaveerref.Videolandia.DTO.DadosAtualizaVideo;
import com.jotaveerref.Videolandia.DTO.DadosRegistroVideo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Video")
@Table(name = "videos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    private boolean ativo;

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

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void exclusaoLogica(){
        this.ativo = false;
    }

    public void ativaVideo() {
        this.ativo = true;
    }
}
