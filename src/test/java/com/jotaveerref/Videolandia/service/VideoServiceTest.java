package com.jotaveerref.Videolandia.service;

import com.jotaveerref.Videolandia.domain.categoria.Categoria;
import com.jotaveerref.Videolandia.domain.video.DadosAtualizaVideo;
import com.jotaveerref.Videolandia.domain.video.DadosRegistroVideo;
import com.jotaveerref.Videolandia.domain.video.Video;
import com.jotaveerref.Videolandia.repository.CateogriaRepository;
import com.jotaveerref.Videolandia.repository.VideoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {

    private DadosRegistroVideo dados;

    private DadosAtualizaVideo dadosAtualizaVideo;

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private CateogriaRepository cateogriaRepository;

    @Mock
    private Categoria categoria;
    @Mock
    private Video video;
    @InjectMocks
    private VideoService service;

    @Captor
    private ArgumentCaptor<Video> videoCaptor;

    @Test
    void deveriaRegistrarVideo(){

        //ARRANGE
        this.dados = new DadosRegistroVideo("VideoTeste", "descrição do video teste", "url teste", 1L);
        BDDMockito.given(videoRepository.existsByUrl(dados.url())).willReturn(false);
        BDDMockito.given(cateogriaRepository.existsById(dados.categoriaId())).willReturn(true);
        BDDMockito.given(cateogriaRepository.findById(dados.categoriaId())).willReturn(Optional.of(categoria));


        //ACT


        //ASSERT
        Assertions.assertDoesNotThrow(() -> service.registrar(dados));
        Assertions.assertEquals(cateogriaRepository.findById(dados.categoriaId()), Optional.of(categoria));
        BDDMockito.then(videoRepository).should().save(videoCaptor.capture());
    }

    @Test
    void deveriaRetornarVideoPorId(){
        this.dadosAtualizaVideo = new DadosAtualizaVideo(1L, "teste", "teste", "teste");
        BDDMockito.given(videoRepository.getReferenceById(1L)).willReturn(video);
        BDDMockito.given(video.isAtivo()).willReturn(true);


        Assertions.assertEquals(videoRepository.getReferenceById(dadosAtualizaVideo.id()), video);
        Assertions.assertDoesNotThrow(() -> service.retornaVideoPorId(1L));
        Assertions.assertEquals(service.retornaVideoPorId(1L), video);

    }

    @Test
    void deveriaAtivarVideoPorId(){

        BDDMockito.given(videoRepository.getReferenceById(1L)).willReturn(video);
        BDDMockito.given(video.isAtivo()).willReturn(false);



        Assertions.assertEquals(videoRepository.getReferenceById(1L), video);
        Assertions.assertDoesNotThrow(() -> service.ativarVideoPorId(1L));
        Assertions.assertEquals(service.ativarVideoPorId(1L), video);

    }


}
