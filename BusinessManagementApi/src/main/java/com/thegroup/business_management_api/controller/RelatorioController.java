package com.thegroup.business_management_api.controller;

import com.thegroup.business_management_api.model.Cliente;
import com.thegroup.business_management_api.service.RelatorioContatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatorios") // http://localhost:8080/relatorios
public class RelatorioController {

    @Autowired
    private RelatorioContatosService service;

    @PostMapping("/adicionar/{id}")
    public ResponseEntity<String> adicionarCliente(@PathVariable Long id) {
        service.adicionarContato(id);
        return ResponseEntity.ok("Cliente com ID " + id + " adicionado com sucesso.");
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<String> removerCliente(@PathVariable Long id) {
        service.removerContato(id);
        return ResponseEntity.ok("Cliente com ID " + id + " removido da fila.");
    }

    @GetMapping("/fila")
    public ResponseEntity<List<Cliente>> listarFila() {
        return ResponseEntity.ok(service.getFilaEspera());
    }

    @GetMapping("/gerar")
    public ResponseEntity<String> gerarRelatorio() {
        String textoRelatorio = service.exportarDados();
        return ResponseEntity.ok(textoRelatorio);
    }
}
