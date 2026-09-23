package com.projetosoftware.ai.observer;

import com.projetosoftware.ai.model.Avaliacao;
import jakarta.persistence.PostPersist;

public class AvaliacaoEntityListener {

    @PostPersist
    public void aposSalvar(Avaliacao avaliacao) {
        AvaliacaoSubject.notificar(avaliacao);
    }
}