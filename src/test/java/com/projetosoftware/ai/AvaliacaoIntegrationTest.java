package com.projetosoftware.ai;

import com.projetosoftware.ai.model.Avaliacao;
import com.projetosoftware.ai.repository.AvaliacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class AvaliacaoIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private AvaliacaoRepository repository;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        repository.deleteAll();
    }

    private Avaliacao salvarAvaliacao(String autor, boolean deletado) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setAutor(autor);
        avaliacao.setConteudo("Conteudo");
        avaliacao.setNota(5);
        avaliacao.setDataAvaliacao(LocalDate.of(2026, 9, 23));
        avaliacao.setDeletado(deletado);
        return repository.save(avaliacao);
    }

    @Test
    void postCriaAvaliacao() throws Exception {
        String json = """
                {"autor": "Nome", "conteudo": "teste", "nota": 5, "dataAvaliacao": "2026-09-23"}
                """;

        mockMvc.perform(post("/avaliacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.conteudo").value("Nome"));

        assertEquals(1, repository.count());
    }

    @Test
    void postComDadosInvalidosRetorna400() throws Exception {
        mockMvc.perform(post("/avaliacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"autor\": \"\"}"))
                .andExpect(status().isBadRequest());
    }

}