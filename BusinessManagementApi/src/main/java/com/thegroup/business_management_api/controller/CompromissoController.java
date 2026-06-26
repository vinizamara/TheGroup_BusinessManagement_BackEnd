package com.thegroup.business_management_api.controller;

import com.thegroup.business_management_api.model.Compromisso;
import com.thegroup.business_management_api.service.CompromissoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compromissos")
public class CompromissoController {

    private CompromissoService service;

    public CompromissoController(CompromissoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Compromisso>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compromisso> buscarPorId(@PathVariable Long id) {

        Optional<Compromisso> compromisso = service.buscarPorId(id);

        if (compromisso.isPresent()) {
            return ResponseEntity.ok(compromisso.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Compromisso> cadastrar(@RequestBody Compromisso compromisso) {
        Compromisso novo = service.cadastrar(compromisso);
        if (novo != null) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(novo.getIdCompromisso()).toUri();
            return ResponseEntity.created(uri).body(novo);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compromisso> atualizar(
            @PathVariable Long id,
            @RequestBody Compromisso compromisso) {

        Compromisso alterado = service.atualizar(id, compromisso);

        if (alterado != null) {
            return ResponseEntity.ok(alterado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        return service.excluir(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Compromisso> concluir(@PathVariable Long id) {

        Compromisso compromisso = service.marcarComoConcluido(id);

        if (compromisso != null) {
            return ResponseEntity.ok(compromisso);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/alerta")
    public ResponseEntity<String> gerarAlerta(@PathVariable Long id) {

        String alerta = service.gerarAlerta(id);

        if (alerta != null) {
            return ResponseEntity.ok(alerta);
        }

        return ResponseEntity.notFound().build();
    }
}
