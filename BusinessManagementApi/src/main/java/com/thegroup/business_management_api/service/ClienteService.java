package com.thegroup.business_management_api.service;

import com.thegroup.business_management_api.model.Cliente;
import com.thegroup.business_management_api.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository repo;

    // injeção via construtor
    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    public List<Cliente> vizualizarTodos() {
        return repo.findAll();
    }

    public Optional<Cliente> vizualizarDetalhes(Long id) {
        return repo.findById(id);
    }

    // metodo para validar o cpnj
    public boolean validarCpnj(String cpnj) {
        return cpnj != null && cpnj.replaceAll("\\D", "").length() == 14;
    }

    public Cliente cadastrarCliente(Cliente c) {
        if (validarCpnj(c.getCnpj())) {
            return repo.save(c); // faz o insert se o cpnj for valido
        }
        return null;
    }

    public Cliente atualizarCliente(Long id, Cliente alterado) {
        if (repo.existsById(id)) {
            // se existe, armazena o registro atual para nao perder os outros campos
            Optional<Cliente> atual = repo.findById(id);
            if (atual.isPresent()) {
                Cliente existente = atual.get();
                existente.setNomeEmpresa(alterado.getNomeEmpresa());
                existente.setNomeResponsavel(alterado.getNomeResponsavel());
                existente.setTelefone(alterado.getTelefone());
                existente.setEmail(alterado.getEmail());

                return repo.save(existente); // tem id, vai fazer update
            }
        }
        return null;
    }

    public boolean excluirCliente(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true; // removido
        }
        return false; // nao removeu
    }
}
