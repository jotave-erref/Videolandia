package com.jotaveerref.Videolandia.domain.categoria;

import com.jotaveerref.Videolandia.domain.video.Video;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Categoria")
@Table(name = "categorias")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Cor cor;

    @OneToMany(mappedBy = "categoria")
    private List<Video> video;

    public Categoria(DadosCategoria dados) {
        this.id = dados.id();
        this.titulo = dados.titulo();
        this.cor = dados.cor();
    }

    public void atualizaCategoria(Categoria categoria){
        if(categoria.getTitulo() != null){
            this.titulo = categoria.getTitulo();
        }
        if(categoria.getCor() != null)
        this.cor = categoria.getCor();
    }
}
