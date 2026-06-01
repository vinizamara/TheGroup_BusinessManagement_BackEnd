package com.thegroup.business_management_api.service;

import com.thegroup.business_management_api.model.TipoCompromisso;
import com.thegroup.business_management_api.repository.TipoCompromissoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoCompromissoService {

    private TipoCompromissoRepository repo;

    // injeção via construtor
    public TipoCompromissoService(TipoCompromissoRepository repo) {
        this.repo = repo;
    }

    public List<TipoCompromisso> visualizarTipos() {
        return repo.findAll();
    }

    public Optional<TipoCompromisso> visualizarDetalhes(Long id) {
        return repo.findById(id);
    }

    public TipoCompromisso cadastrarTipo(TipoCompromisso tipo) {
        return repo.save(tipo);
    }

    public TipoCompromisso editarTipo(Long id, TipoCompromisso alterado) {
        if (repo.existsById(id)) {
            Optional<TipoCompromisso> atual = repo.findById(id);
            if (atual.isPresent()) {
                TipoCompromisso existente = atual.get();
                existente.setNome(alterado.getNome());
                existente.setDescricao(alterado.getDescricao());

                return repo.save(existente); // faz o update
            }
        }
        return null;
    }

    public boolean excluirTipo(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true; // removido
        }
        return false; // nao removeu
    }
}
