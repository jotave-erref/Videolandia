package com.jotaveerref.Videolandia.controller;

import com.jotaveerref.Videolandia.domain.categoria.Categoria;
import com.jotaveerref.Videolandia.domain.categoria.Cor;
import com.jotaveerref.Videolandia.domain.categoria.DadosCategoria;
import com.jotaveerref.Videolandia.domain.categoria.DadosDetalhamentoCategoria;
import com.jotaveerref.Videolandia.repository.CateogriaRepository;
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
class CategoriaControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCategoria> dadosCategoriaJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoCategoria> dadosDetalhamentoCategoriaJson;
    @MockBean
    private CateogriaRepository repository;
    @MockBean
    private Categoria categoria;
    @Test
    @WithMockUser
    void deveriaRetornarCodigo400() throws Exception {
        var response = mvc.perform(post("/categorias")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @WithMockUser
    void deveriaRetornarCodigo200() throws Exception {
        var cor = Cor.VERMELHO;
        var dadosDetalhamentoCategoria = new DadosDetalhamentoCategoria(categoria);

        when(repository.save(any())).thenReturn(categoria);

        var response = mvc.perform(post("/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCategoriaJson.write(new DadosCategoria(1L, "xxxxx", cor)).getJson()))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosDetalhamentoCategoriaJson.write(dadosDetalhamentoCategoria).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

}