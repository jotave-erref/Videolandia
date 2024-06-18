package com.jotaveerref.Videolandia.controller;

import com.jotaveerref.Videolandia.domain.categoria.*;
import com.jotaveerref.Videolandia.repository.CateogriaRepository;
import com.jotaveerref.Videolandia.service.CategoriaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CateogriaRepository repository;

    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<Page<DadosCategoria>> listar(@PageableDefault(page = 0, size = 10, sort = {"id"}) Pageable pageable){
        Page<DadosCategoria> page = repository.findAll(pageable).map(DadosCategoria::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCategoria> registrar(@RequestBody @Valid DadosCategoria dados, UriComponentsBuilder uri){
        var categoria = repository.save(new Categoria(dados));
        var url = uri.path("{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(url).body(new DadosDetalhamentoCategoria(categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCategoria> buscarPorId(@PathVariable Long id){
        service.verificaId(id);
        var categoriaBuscada = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCategoria(categoriaBuscada));
    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<Page<DadosCategoriaVideos>> buscaCategoriaVideos (@PageableDefault(page = 0, size = 10, sort = {"id"}) Pageable page, @PathVariable Long id){
        var categoriaVideos = service.buscaVideoPorCategoria(id, page);

        return ResponseEntity.ok(categoriaVideos);

    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCategoria> atualizar(@Valid @RequestBody DadosAtualizaCategoria dados){
        var categoria = service.atualizaCategoria(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCategoria(categoria));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        service.verificaId(id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
