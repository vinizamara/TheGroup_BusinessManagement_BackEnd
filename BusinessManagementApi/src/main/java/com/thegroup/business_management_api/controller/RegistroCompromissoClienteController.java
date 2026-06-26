package com.thegroup.business_management_api.controller;

import com.thegroup.business_management_api.model.RegistroCompromissoCliente;
import com.thegroup.business_management_api.service.RegistroCompromissoClienteService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/registros-compromisso-cliente")
public class RegistroCompromissoClienteController {

    private RegistroCompromissoClienteService service;

    public RegistroCompromissoClienteController(
            RegistroCompromissoClienteService service) {
        this.service = service;
    }

    // Retorna todos os registros cadastrados
    @GetMapping
    public ResponseEntity<List<RegistroCompromissoCliente>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // Busca um registro pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<RegistroCompromissoCliente> buscarPorId(
            @PathVariable Long id) {

        Optional<RegistroCompromissoCliente> registro =
                service.buscarPorId(id);

        if (registro.isPresent()) {
            return ResponseEntity.ok(registro.get()); // retorna 200, ok
        }

        return ResponseEntity.notFound().build(); // retorna 404, nao encontrado
    }

    // Cadastra um novo registro
    @Transactional
    @PostMapping
    public ResponseEntity<RegistroCompromissoCliente> cadastrar(@RequestBody RegistroCompromissoCliente registro) {
        RegistroCompromissoCliente novo = service.cadastrar(registro);
        if (novo != null) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(novo.getIdRegistro()).toUri();
            return ResponseEntity.created(uri).body(novo);
        }
        return ResponseEntity.badRequest().build();
    }

    // Atualiza um registro existente
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<RegistroCompromissoCliente> atualizar(
            @PathVariable Long id,
            @RequestBody RegistroCompromissoCliente registro) {

        RegistroCompromissoCliente alterado =
                service.atualizar(id, registro);

        if (alterado != null) {
            return ResponseEntity.ok(alterado); // retorna 200, ok
        }

        return ResponseEntity.notFound().build(); // retorna 404, nao encontrou o id
    }

    // Remove um registro pelo ID
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        return service.excluir(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Atualiza a situação do cliente no compromisso
    @Transactional
    @PatchMapping("/{id}/situacao")
    public ResponseEntity<RegistroCompromissoCliente> atualizarSituacao(
            @PathVariable Long id,
            @RequestParam String situacao) {

        RegistroCompromissoCliente alterado =
                service.atualizarSituacao(id, situacao);

        if (alterado != null) {
            return ResponseEntity.ok(alterado); // retorna 200, ok
        }

        return ResponseEntity.notFound().build(); // retorna 404, nao encontrou o id
    }
}
