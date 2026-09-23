package com.projetosoftware.ai.service;

import com.projetosoftware.ai.model.Avaliacao;
import com.projetosoftware.ai.repository.AvaliacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository repository;

    public AvaliacaoService(AvaliacaoRepository repository) {
        this.repository = repository;
    }

    public List<Avaliacao> listar(String autor) {
        if (autor == null || autor.isBlank()) {
            return repository.findByDeletadoFalse();
        }
        return repository.findByAutorStartingWithIgnoreCaseAndDeletadoFalse(autor);
    }

    public Avaliacao criar(Avaliacao avaliacao) {
        return repository.save(avaliacao);
    }
}