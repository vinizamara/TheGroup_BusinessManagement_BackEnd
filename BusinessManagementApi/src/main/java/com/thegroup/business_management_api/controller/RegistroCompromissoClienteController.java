package com.thegroup.business_management_api.controller;

import com.thegroup.business_management_api.model.RegistroCompromissoCliente;
import com.thegroup.business_management_api.service.RegistroCompromissoClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public ResponseEntity<RegistroCompromissoCliente> cadastrar(
            @RequestBody RegistroCompromissoCliente registro) {

        RegistroCompromissoCliente novo =
                service.cadastrar(registro);

        if (novo != null) {
            return ResponseEntity.ok(novo); // retorna 200, ok
        }

        return ResponseEntity.badRequest().build(); // retorna 400, dados invalidos
    }

    // Atualiza um registro existente
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(
            @PathVariable Long id) {

        if (service.excluir(id)) {
            return ResponseEntity.ok().build(); // retorna 200, ok
        }

        return ResponseEntity.notFound().build(); // retorna 404, id nao existe
    }

    // Atualiza a situação do cliente no compromisso
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
