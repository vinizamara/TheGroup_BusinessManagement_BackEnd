package com.thegroup.business_management_api.service;

import com.thegroup.business_management_api.model.RegistroCompromissoCliente;
import com.thegroup.business_management_api.repository.RegistroCompromissoClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroCompromissoClienteService {

    private RegistroCompromissoClienteRepository repo;

    // injeção via construtor
    public RegistroCompromissoClienteService(RegistroCompromissoClienteRepository repo) {
        this.repo = repo;
    }

    // Retorna todos os registros cadastrados
    public List<RegistroCompromissoCliente> listarTodos() {
        return repo.findAll();
    }

    // Busca um registro pelo ID
    public Optional<RegistroCompromissoCliente> buscarPorId(Long id) {
        return repo.findById(id);
    }

    // Cadastra um novo registro
    @Transactional
    public RegistroCompromissoCliente cadastrar(RegistroCompromissoCliente registro) {
        return repo.save(registro);
    }

    // Atualiza um registro existente
    @Transactional
    public RegistroCompromissoCliente atualizar(Long id, RegistroCompromissoCliente alterado) {

        if (repo.existsById(id)) {

            Optional<RegistroCompromissoCliente> atual = repo.findById(id);

            if (atual.isPresent()) {

                RegistroCompromissoCliente existente = atual.get();

                existente.setDataRegistro(alterado.getDataRegistro());
                existente.setObservacao(alterado.getObservacao());
                existente.setSituacaoClienteNoCompromisso(
                        alterado.getSituacaoClienteNoCompromisso()
                );
                existente.setCliente(alterado.getCliente());
                existente.setCompromisso(alterado.getCompromisso());

                return repo.save(existente);
            }
        }

        return null;
    }

    // Remove um registro pelo ID
    @Transactional
    public boolean excluir(Long id) {

        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }

        return false;
    }

    // Atualiza a situação do cliente no compromisso
    @Transactional
    public RegistroCompromissoCliente atualizarSituacao(
            Long id,
            String situacao) {

        Optional<RegistroCompromissoCliente> registro = repo.findById(id);

        if (registro.isPresent()) {

            RegistroCompromissoCliente existente = registro.get();

            existente.atualizarSituacao(situacao);

            return repo.save(existente);
        }

        return null;
    }
}
