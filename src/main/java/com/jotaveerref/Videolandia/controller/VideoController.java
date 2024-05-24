package com.jotaveerref.Videolandia.controller;

import com.jotaveerref.Videolandia.DTO.DadosAtualizaVideo;
import com.jotaveerref.Videolandia.DTO.DadosRegistroVideo;
import com.jotaveerref.Videolandia.DTO.DadosVideo;
import com.jotaveerref.Videolandia.infra.exceptions.ValidadorException;
import com.jotaveerref.Videolandia.repository.VideoRepository;
import com.jotaveerref.Videolandia.service.VideoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("videos")
public class VideoController {

    @Autowired
    private VideoRepository repository;

    @Autowired
    private VideoService videoService;

    @GetMapping
    public ResponseEntity<Page<DadosVideo>> listar(@PageableDefault(size = 10, page = 0, sort = {"titulo"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosVideo::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosVideo> retornaVideoId(@PathVariable Long id){
        var video = repository.getReferenceById(id);
        if(!video.isAtivo()){
            throw new ValidadorException("Este vídeo está removido da lista!");
        }


        return ResponseEntity.ok(new DadosVideo(video));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosRegistroVideo> registra(@Valid @RequestBody DadosRegistroVideo dados, UriComponentsBuilder uri){
        var video = videoService.registrar(dados);
        var url = uri.path("videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(url).body(new DadosRegistroVideo(video));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosAtualizaVideo> atualizar(@Valid @RequestBody DadosAtualizaVideo dados){
        var video = videoService.atualizaVideo(dados);
        return ResponseEntity.ok(new DadosAtualizaVideo(video));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity eclusaoLogica(@PathVariable Long id){
        var video = repository.getReferenceById(id);
        video.exclusaoLogica();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity ativarVideo(@PathVariable Long id){
        var video = repository.getReferenceById(id);
        if(!video.isAtivo()){
            video.ativaVideo();
        }else{
            throw new ValidadorException("Este vídeo já está ativo na lista!");
        }
        return ResponseEntity.ok().body(new DadosVideo(video));
    }
}
