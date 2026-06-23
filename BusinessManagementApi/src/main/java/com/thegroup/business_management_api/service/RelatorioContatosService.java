package com.thegroup.business_management_api.service;

import com.thegroup.business_management_api.model.Cliente;
import com.thegroup.business_management_api.model.Exportavel;
import com.thegroup.business_management_api.model.FilaRelatorio;
import com.thegroup.business_management_api.repository.ClienteRepository;
import com.thegroup.business_management_api.repository.FilaRelatorioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioContatosService implements Exportavel {

    private final ClienteRepository clienteRepository;
    private final FilaRelatorioRepository filaRelatorioRepository;

    public RelatorioContatosService(ClienteRepository clienteRepository,
                                    FilaRelatorioRepository filaRelatorioRepository) {
        this.clienteRepository = clienteRepository;
        this.filaRelatorioRepository = filaRelatorioRepository;
    }

    @Transactional
    public void adicionarContato(Long id) {
        clienteRepository.findById(id).ifPresent(cliente -> {
            FilaRelatorio itemFila = new FilaRelatorio();
            itemFila.setCliente(cliente);
            filaRelatorioRepository.save(itemFila);
        });
    }

    @Transactional
    public void removerContato(Long id) {
        List<FilaRelatorio> todosDaFila = filaRelatorioRepository.findAll();
        for (FilaRelatorio item : todosDaFila) {
            if (item.getCliente().getIdCliente().equals(id)) {
                filaRelatorioRepository.delete(item);
            }
        }
    }

    @Transactional
    @Override
    public String exportarDados() {
        List<FilaRelatorio> listaBanco = filaRelatorioRepository.findAll();

        if (listaBanco.isEmpty()) {
            return "Nenhum contato na fila do relatório.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("-- RELATÓRIO DE CONTATOS --\n");

        for (FilaRelatorio item : listaBanco) {
            Cliente c = item.getCliente();
            sb.append("\nEmpresa: ").append(c.getNomeEmpresa())
                    .append(" | CNPJ: ").append(c.getCnpj())
                    .append(" | Telefone: ").append(c.getTelefone())
                    .append(" | Email: ").append(c.getEmail())
                    .append("\n");
        }
        sb.append("\n--------------");

        filaRelatorioRepository.deleteAll();

        return sb.toString();
    }

    public List<Cliente> getFilaEspera() {
        return filaRelatorioRepository.findAll().stream()
                .map(FilaRelatorio::getCliente)
                .collect(java.util.stream.Collectors.toList());
    }
}