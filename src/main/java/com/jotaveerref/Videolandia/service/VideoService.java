package com.jotaveerref.Videolandia.service;

import com.jotaveerref.Videolandia.DTO.DadosAtualizaVideo;
import com.jotaveerref.Videolandia.DTO.DadosRegistroVideo;
import com.jotaveerref.Videolandia.domain.Video;
import com.jotaveerref.Videolandia.infra.exceptions.ValidadorException;
import com.jotaveerref.Videolandia.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public Video registrar(DadosRegistroVideo dados){
        if(videoRepository.existsByUrl(dados.url())){
            throw new ValidadorException("Este video já está cadastrado!");
        }

        return videoRepository.save(new Video(dados));
    }

    public Video atualizaVideo(DadosAtualizaVideo dados){
        if(!videoRepository.existsById(dados.id())){
            throw new ValidadorException("Id do vídeo deve ser informado");
        }
        var video = videoRepository.getReferenceById(dados.id());
        if(video.isAtivo()){
            video.atualizaVideo(dados);
        }else{
            throw new ValidadorException("Este vídeo foi exlcuído da sua lista!");
        }

        return videoRepository.save(video);
    }

}
