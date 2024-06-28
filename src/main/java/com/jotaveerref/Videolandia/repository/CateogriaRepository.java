package com.jotaveerref.Videolandia.repository;

import com.jotaveerref.Videolandia.domain.categoria.Categoria;
import com.jotaveerref.Videolandia.domain.video.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CateogriaRepository extends JpaRepository<Categoria, Long> {

    @Query("select v from Categoria c join c.video v where c.id = :id")
    List<Video> findByCategoriaIdByVideo(Long id);
}
