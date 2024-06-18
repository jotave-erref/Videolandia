package com.jotaveerref.Videolandia.service;

import com.jotaveerref.Videolandia.domain.categoria.Categoria;
import com.jotaveerref.Videolandia.domain.categoria.DadosAtualizaCategoria;
import com.jotaveerref.Videolandia.domain.categoria.DadosCategoriaVideos;
import com.jotaveerref.Videolandia.infra.exceptions.ValidadorException;
import com.jotaveerref.Videolandia.repository.CateogriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CategoriaService {
    @Autowired
    private CateogriaRepository repository;

    public void verificaId(Long id){
        if(!repository.existsById(id)){
            throw new ValidadorException("Categoria não encontrada");
        }
    }

    public Page<DadosCategoriaVideos> buscaVideoPorCategoria(Long id, @PageableDefault(page = 0, size = 10, sort = {"id"}) Pageable page){
        verificaId(id);
        var  categoriaVideos = repository.findByCategoriaIdByVideo(id).stream().map(v -> new DadosCategoriaVideos(
                        v.getCategoria().getId(), v.getTitulo(), v.getDescricao(), v.getUrl()))
                .collect(Collectors.toList());

        //Transforma a lista em page e retorna o conteúdo
        return PageableExecutionUtils.getPage(categoriaVideos, page, categoriaVideos::size);
    }

    public Categoria atualizaCategoria(DadosAtualizaCategoria dados){
        verificaId(dados.id());
        var categoria = repository.getReferenceById(dados.id());
        categoria.atualizaCategoria(categoria);
        return categoria;
    }

}
