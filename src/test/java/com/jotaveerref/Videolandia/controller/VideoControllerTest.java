package com.jotaveerref.Videolandia.controller;

import com.jotaveerref.Videolandia.domain.categoria.Categoria;
import com.jotaveerref.Videolandia.domain.video.DadosDetalhamentoVideo;
import com.jotaveerref.Videolandia.domain.video.DadosRegistroVideo;
import com.jotaveerref.Videolandia.domain.video.Video;
import com.jotaveerref.Videolandia.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class VideoControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DadosRegistroVideo> dadosRegistroVideoJson;
    @Autowired
    private JacksonTester<DadosDetalhamentoVideo> dadosDetalhamentoVideoJson;

    @MockBean
    private VideoService service;

    @MockBean
    private Categoria categoria;

    @Test
    @WithMockUser
    void deveriaRetornarCodigo400() throws Exception {
        var response = mvc.perform(post("/videos")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    @WithMockUser
    void deveriaRetornarCodigo200() throws Exception {
        Video video = new Video(1L, "xxxx", "xxx", "xxx", true, categoria);
        var dadosDetalhamentoVideo = new DadosDetalhamentoVideo(video);


        when(service.registrar(any())).thenReturn(video);

        var response = mvc.perform(post("/videos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosRegistroVideoJson.write(new DadosRegistroVideo("xxxx", "xxxxx", "xxxx", 3L)).getJson()))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosDetalhamentoVideoJson.write(dadosDetalhamentoVideo).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}