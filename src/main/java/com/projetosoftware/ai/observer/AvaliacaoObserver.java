package com.projetosoftware.ai.observer;

import com.projetosoftware.ai.model.Avaliacao;

public interface AvaliacaoObserver {
    void onNovaAvaliacao(Avaliacao avaliacao);
}
