package com.jotaveerref.Videolandia.repository;

import com.jotaveerref.Videolandia.domain.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Page<Video> findAllByAtivoTrue(Pageable paginacao);

    boolean existsByUrl(String url);

    @Query("select v from Video v where v.id = :id and v.ativo = true")
    boolean existsByAtivoTrue(Long id);
}
