package com.thegroup.business_management_api.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "registros_compromisso_cliente")
public class RegistroCompromissoCliente {

    // Identificador único do registro
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistro;

    // Informações do vínculo entre cliente e compromisso
    private LocalDate dataRegistro;

    private String observacao;

    private String situacaoClienteNoCompromisso;

    // Cliente associado ao registro
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    // Compromisso associado ao registro
    @ManyToOne
    @JoinColumn(name = "id_compromisso")
    private Compromisso compromisso;

    // Construtor padrão exigido pelo JPA
    public RegistroCompromissoCliente() {
        this.dataRegistro = LocalDate.now();
    }

    // Regras de negócio da entidade
    public void atualizarSituacao(String situacao) {
        this.situacaoClienteNoCompromisso = situacao;
    }

    // Getters e Setters
    public Long getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Long idRegistro) {
        this.idRegistro = idRegistro;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getSituacaoClienteNoCompromisso() {
        return situacaoClienteNoCompromisso;
    }

    public void setSituacaoClienteNoCompromisso(String situacaoClienteNoCompromisso) {
        this.situacaoClienteNoCompromisso = situacaoClienteNoCompromisso;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Compromisso getCompromisso() {
        return compromisso;
    }

    public void setCompromisso(Compromisso compromisso) {
        this.compromisso = compromisso;
    }
}
