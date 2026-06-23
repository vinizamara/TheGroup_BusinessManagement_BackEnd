package com.thegroup.business_management_api.controller;

import com.thegroup.business_management_api.model.Agenda;
import com.thegroup.business_management_api.service.AgendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agendas")
public class AgendaController {

    private AgendaService service;

    public AgendaController(AgendaService service) {
        this.service = service;
    }

    // Retorna todas as agendas cadastradas
    @GetMapping
    public ResponseEntity<List<Agenda>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    // Busca uma agenda pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Agenda> buscarPorId(@PathVariable Long id) {

        Optional<Agenda> agenda = service.buscarPorId(id);

        if (agenda.isPresent()) {
            return ResponseEntity.ok(agenda.get()); // retorna 200, ok
        }

        return ResponseEntity.notFound().build(); // retorna 404, nao encontrada
    }

    // Cadastra uma nova agenda
    @PostMapping
    public ResponseEntity<Agenda> cadastrar(@RequestBody Agenda agenda) {
        Agenda nova = service.cadastrar(agenda);
        if (nova != null) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(nova.getIdAgenda()).toUri();
            return ResponseEntity.created(uri).body(nova);
        }
        return ResponseEntity.badRequest().build();
    }

    // Atualiza uma agenda existente
    @PutMapping("/{id}")
    public ResponseEntity<Agenda> atualizar(
            @PathVariable Long id,
            @RequestBody Agenda agenda) {

        Agenda alterada = service.atualizar(id, agenda);

        if (alterada != null) {
            return ResponseEntity.ok(alterada); // retorna 200, ok
        }

        return ResponseEntity.notFound().build(); // retorna 404, nao encontrou o id
    }

    // Remove uma agenda pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        return service.excluir(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Retorna os compromissos pendentes de uma agenda
    @GetMapping("/{id}/pendentes")
    public ResponseEntity<List<?>> listarPendentes(@PathVariable Long id) {

        Agenda agenda = service.visualizarPendentes(id);

        if (agenda != null) {
            return ResponseEntity.ok(agenda.listarPendentes()); // retorna 200, ok
        }

        return ResponseEntity.notFound().build(); // retorna 404, agenda nao encontrada
    }
}