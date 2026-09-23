package com.projetosoftware.ai.controller;

import com.projetosoftware.ai.model.Avaliacao;
import com.projetosoftware.ai.service.AvaliacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoService service;

    public AvaliacaoController(AvaliacaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Avaliacao> listar(@RequestParam(required = false) String autor) {
        return service.listar(autor);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Avaliacao criar(@RequestBody @Valid Avaliacao avaliacao) {
        return service.criar(avaliacao);
    }
}