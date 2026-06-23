package com.thegroup.business_management_api.service;

import com.thegroup.business_management_api.model.Compromisso;
import com.thegroup.business_management_api.repository.CompromissoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompromissoService {

    private CompromissoRepository repo;

    // Injeção de dependência via construtor
    public CompromissoService(CompromissoRepository repo) {
        this.repo = repo;
    }

    // Retorna todos os compromissos cadastrados
    public List<Compromisso> listarTodos() {
        return repo.findAll();
    }

    // Busca um compromisso pelo ID
    public Optional<Compromisso> buscarPorId(Long id) {
        return repo.findById(id);
    }

    // Cadastra um novo compromisso
    @Transactional
    public Compromisso cadastrar(Compromisso compromisso) {
        return repo.save(compromisso);
    }

    // Atualiza um compromisso existente
    @Transactional
    public Compromisso atualizar(Long id, Compromisso alterado) {

        if (repo.existsById(id)) {

            Optional<Compromisso> atual = repo.findById(id);

            if (atual.isPresent()) {

                Compromisso existente = atual.get();

                existente.setTitulo(alterado.getTitulo());
                existente.setPregao(alterado.getPregao());
                existente.setDescricao(alterado.getDescricao());
                existente.setData(alterado.getData());
                existente.setHorario(alterado.getHorario());
                existente.setStatusCompromisso(alterado.getStatusCompromisso());
                existente.setAntecedenciaAlerta(alterado.getAntecedenciaAlerta());
                existente.setTipoCompromisso(alterado.getTipoCompromisso());

                return repo.save(existente);
            }
        }

        return null;
    }

    // Remove um compromisso pelo ID
    @Transactional
    public boolean excluir(Long id) {

        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }

        return false;
    }

    // Marca um compromisso como concluído
    public Compromisso marcarComoConcluido(Long id) {

        Optional<Compromisso> compromisso = repo.findById(id);

        if (compromisso.isPresent()) {

            Compromisso existente = compromisso.get();

            existente.marcarComoConcluido();

            return repo.save(existente);
        }

        return null;
    }

    // Gera um alerta para um compromisso específico
    public String gerarAlerta(Long id) {

        Optional<Compromisso> compromisso = repo.findById(id);

        if (compromisso.isPresent()) {
            return compromisso.get().gerarAlerta();
        }

        return null;
    }
}