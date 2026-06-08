package com.thegroup.business_management_api.service;

import com.thegroup.business_management_api.model.Cliente;
import com.thegroup.business_management_api.model.Exportavel;
import com.thegroup.business_management_api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelatorioContatosService implements Exportavel {

    private List<Cliente> filaEspera = new ArrayList<>();

    @Autowired
    private ClienteRepository repository;

    public void adicionarContato(Long id) {
        repository.findById(id).ifPresent(filaEspera::add);
    }

    public void removerContato(Long id) {
        filaEspera.removeIf(cliente -> cliente.getIdCliente().equals(id));
    }

    @Override
    public String exportarDados() {
        if (filaEspera.isEmpty()) {
            return "Nenhum contato na fila do relatório.";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("-- RELATÓRIO DE CONTATOS --\n");
        for (Cliente c : filaEspera) {
            sb.append("\nEmpresa: ").append(c.getNomeEmpresa())
                    .append(" | CNPJ: ").append(c.getCnpj())
                    .append(" | Telefone: ").append(c.getTelefone())
                    .append(" | Email: ").append(c.getEmail())
                    .append("\n");
        }
        sb.append("\n--------------");
        filaEspera.clear();

        return sb.toString();
    }

    // getters e setters
    public List<Cliente> getFilaEspera() {
        return filaEspera;
    }

    public void setFilaEspera(List<Cliente> filaEspera) {
        this.filaEspera = filaEspera;
    }
}