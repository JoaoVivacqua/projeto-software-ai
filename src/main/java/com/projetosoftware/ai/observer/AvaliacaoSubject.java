package com.projetosoftware.ai.observer;

import com.projetosoftware.ai.model.Avaliacao;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoSubject {
    private static final List<AvaliacaoObserver> observers = new ArrayList<>();

    public static void registrar(AvaliacaoObserver observer) {
        observers.add(observer);
    }

    public static void remover(AvaliacaoObserver observer) {
        observers.remove(observer);
    }

    public static void notificar(Avaliacao avaliacao) {
        for (AvaliacaoObserver observer : observers) {
            observer.onNovaAvaliacao(avaliacao);
        }
    }
}
