package com.jotaveerref.Videolandia.controller;

import com.jotaveerref.Videolandia.domain.video.DadosAtualizaVideo;
import com.jotaveerref.Videolandia.domain.video.DadosDetalhamentoVideo;
import com.jotaveerref.Videolandia.domain.video.DadosRegistroVideo;
import com.jotaveerref.Videolandia.domain.video.DadosVideo;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("videos")
public class VideoController {

    @Autowired
    private VideoRepository repository;

    @Autowired
    private VideoService videoService;

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoVideo>> listar(@PageableDefault(size = 10, page = 0, sort = {"titulo"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosDetalhamentoVideo::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoVideo> retornaVideoId(@PathVariable Long id){
        var video = videoService.retornaVideoPorId(id);

        return ResponseEntity.ok(new DadosDetalhamentoVideo(video));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoVideo> registra(@Valid @RequestBody DadosRegistroVideo dados, UriComponentsBuilder uri){
        var video = videoService.registrar(dados);

        var url = uri.path("videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(url).body(new DadosDetalhamentoVideo(video));
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
        videoService.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DadosVideo> ativarVideo(@PathVariable Long id){

        var video = videoService.ativarVideoPorId(id);
        return ResponseEntity.ok().body(new DadosVideo(video));
    }

    @GetMapping("search")
    public ResponseEntity<List<DadosVideo>> retornaVideoTitulo(@RequestParam String titulo){

        var video = videoService.retornaVideoPorTitulo(titulo);
        return ResponseEntity.ok(video);
    }
}
