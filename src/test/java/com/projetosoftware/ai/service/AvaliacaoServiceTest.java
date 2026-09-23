package com.projetosoftware.ai.service;

import com.projetosoftware.ai.model.Avaliacao;
import com.projetosoftware.ai.repository.AvaliacaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AvaliacaoServiceTest {

    @Mock
    private AvaliacaoRepository repository;

    @InjectMocks
    private AvaliacaoService service;

    @Test
    void listarSemFiltroRetornaNaoDeletados() {
        List<Avaliacao> avaliacoes = List.of(new Avaliacao());
        when(repository.findByDeletadoFalse()).thenReturn(avaliacoes);

        List<Avaliacao> resultado = service.listar(null);

        assertEquals(avaliacoes, resultado);
        verify(repository).findByDeletadoFalse();
    }

    @Test
    void listarComFiltroEmBrancoRetornaNaoDeletados() {
        List<Avaliacao> avaliacoes = List.of(new Avaliacao());
        when(repository.findByDeletadoFalse()).thenReturn(avaliacoes);

        List<Avaliacao> resultado = service.listar("   ");

        assertEquals(avaliacoes, resultado);
        verify(repository).findByDeletadoFalse();
    }

    @Test
    void listarComFiltroUsaStartingWith() {
        List<Avaliacao> avaliacoes = List.of(new Avaliacao());
        when(repository.findByAutorStartingWithIgnoreCaseAndDeletadoFalse("Ja")).thenReturn(avaliacoes);

        List<Avaliacao> resultado = service.listar("Ja");

        assertEquals(avaliacoes, resultado);
        verify(repository).findByAutorStartingWithIgnoreCaseAndDeletadoFalse("Ja");
    }

    @Test
    void criarSalvaAvaliacao() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setAutor("Java");
        when(repository.save(avaliacao)).thenReturn(avaliacao);

        Avaliacao resultado = service.criar(avaliacao);

        assertEquals("Java", resultado.getAutor());
        verify(repository).save(avaliacao);
    }
}