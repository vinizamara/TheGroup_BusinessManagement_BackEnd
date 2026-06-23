package com.thegroup.business_management_api.controller;


import com.thegroup.business_management_api.model.Cliente;
import com.thegroup.business_management_api.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(service.vizualizarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        Optional<Cliente> cliente = service.vizualizarDetalhes(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get()); // retorna 200, ok
        }
        return ResponseEntity.notFound().build(); // retorna 404, nao encontrado
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) {
        Cliente novo = service.cadastrarCliente(cliente);
        if (novo != null) {
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(novo.getIdCliente()).toUri();

            return ResponseEntity.created(uri).body(novo); // Retorna 201 Created com Location
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente alterado = service.atualizarCliente(id, cliente);
        if (alterado != null) {
            return ResponseEntity.ok(alterado); // retorna 200, ok
        }
        return ResponseEntity.notFound().build(); // retorna 404, nao encontrou o id
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (service.excluirCliente(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}