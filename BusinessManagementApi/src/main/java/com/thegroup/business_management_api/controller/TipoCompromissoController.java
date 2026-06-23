package com.thegroup.business_management_api.controller;

import com.thegroup.business_management_api.model.TipoCompromisso;
import com.thegroup.business_management_api.service.TipoCompromissoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipos-compromisso")
public class TipoCompromissoController {

    private TipoCompromissoService service;

    public TipoCompromissoController(TipoCompromissoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TipoCompromisso>> listar() {
        return ResponseEntity.ok(service.visualizarTipos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCompromisso> buscarPorId(@PathVariable Long id) {
        Optional<TipoCompromisso> tipo = service.visualizarDetalhes(id);
        if (tipo.isPresent()) {
            return ResponseEntity.ok(tipo.get()); // retorna 200, ok
        }
        return ResponseEntity.notFound().build(); // retorna 404, nao encontrado
    }

    @PostMapping
    public ResponseEntity<TipoCompromisso> cadastrar(@RequestBody TipoCompromisso tipo) {
        TipoCompromisso novo = service.cadastrarTipo(tipo);
        if (novo != null) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(novo.getIdTipoCompromisso()).toUri();
            return ResponseEntity.created(uri).body(novo);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoCompromisso> atualizar(@PathVariable Long id, @RequestBody TipoCompromisso tipo) {
        TipoCompromisso alterado = service.editarTipo(id, tipo);
        if (alterado != null) {
            return ResponseEntity.ok(alterado); // retorna 200, ok
        }
        return ResponseEntity.notFound().build(); // retorna 404, nao encontrou o id
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        return service.excluirTipo(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
