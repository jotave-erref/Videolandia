package com.jotaveerref.Videolandia.service;

import com.jotaveerref.Videolandia.domain.video.*;
import com.jotaveerref.Videolandia.infra.exceptions.ValidadorException;
import com.jotaveerref.Videolandia.repository.CateogriaRepository;
import com.jotaveerref.Videolandia.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CateogriaRepository cateogriaRepository;

    public Video registrar(DadosRegistroVideo dados){
        if(videoRepository.existsByUrl(dados.url())){
            throw new ValidadorException("Este video já está cadastrado!");
        }
        if(!cateogriaRepository.existsById(dados.categoriaId())){
            throw new ValidadorException("Categoria informada não existe");
        }

        var categoria = cateogriaRepository.findById(dados.categoriaId()).get();

        var video = new Video(dados);
        video.setCategoria(categoria);

        return videoRepository.save(video);
    }

    public Video retornaVideoPorId(Long id){
        var video = videoRepository.getReferenceById(id);
        if(!videoRepository.existsById(id) || !video.isAtivo()){
            throw new ValidadorException("Vídeo não encontrado");
        }
        return video;
    }

    public Video atualizaVideo(DadosAtualizaVideo dados){
        if(!videoRepository.existsById(dados.id())){
            throw new ValidadorException("Vídeo não encontrado");
        }
        var video = videoRepository.getReferenceById(dados.id());
        if(video.isAtivo()){
            video.atualizaVideo(dados);
        }else{
            throw new ValidadorException("Este vídeo está inativo na lista!");
        }

        return videoRepository.save(video);
    }

    public void excluirPorId(Long id){
        var video = videoRepository.getReferenceById(id);
        if(video.isAtivo()){
            video.exclusaoLogica();
        }else{
            throw new ValidadorException("Este vídeo já foi excluído da lista!");
        }
    }

    public Video ativarVideoPorId(Long id){
        var video = videoRepository.getReferenceById(id);
        if(!video.isAtivo()){
            video.ativaVideo();
        }else{
            throw new ValidadorException("Este vídeo já está ativo na lista!");
        }
        return video;
    }

    public List<DadosVideo> retornaVideoPorTitulo(String titulo){
        var video = videoRepository.findByTitleContainingIgnoreCase(titulo).stream().map(DadosVideo::new).toList();
        if(videoRepository.findByTitleContainingIgnoreCase(titulo).isEmpty()){
            throw new ValidadorException("Nenhum vídeo encontrado!");
        }
        return video;
    }

}
