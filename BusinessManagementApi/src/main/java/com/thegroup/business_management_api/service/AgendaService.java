package com.thegroup.business_management_api.service;

import com.thegroup.business_management_api.model.Agenda;
import com.thegroup.business_management_api.repository.AgendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    private AgendaRepository repo;

    // Injeção de dependência via construtor
    public AgendaService(AgendaRepository repo) {
        this.repo = repo;
    }

    // Retorna todas as agendas cadastradas
    public List<Agenda> listarTodas() {
        return repo.findAll();
    }

    // Busca uma agenda pelo ID
    public Optional<Agenda> buscarPorId(Long id) {
        return repo.findById(id);
    }

    // Cadastra uma nova agenda
    public Agenda cadastrar(Agenda agenda) {
        return repo.save(agenda);
    }

    // Atualiza uma agenda existente
    public Agenda atualizar(Long id, Agenda alterada) {

        if (repo.existsById(id)) {

            Optional<Agenda> atual = repo.findById(id);

            if (atual.isPresent()) {

                Agenda existente = atual.get();

                existente.setNome(alterada.getNome());

                return repo.save(existente);
            }
        }

        return null;
    }

    // Remove uma agenda pelo ID
    public boolean excluir(Long id) {

        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }

        return false;
    }

    // Retorna os compromissos pendentes da agenda
    public Agenda visualizarPendentes(Long id) {

        Optional<Agenda> agenda = repo.findById(id);

        if (agenda.isPresent()) {
            return agenda.get();
        }

        return null;
    }
}