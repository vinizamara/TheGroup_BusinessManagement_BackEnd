package com.thegroup.business_management_api.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    private String nomeEmpresa;
    private String nomeResponsavel;

    @Column(name = "cnpj")
    private String cnpj;

    private String ramo;
    private boolean participouLicitacoes;
    private String origemContato;
    private String telefone;
    private String email;
    private String observacao;

    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    // construtor padrão
    public Cliente() {
    }

    // getters e setters
    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public boolean isParticipouLicitacoes() {
        return participouLicitacoes;
    }

    public void setParticipouLicitacoes(boolean participouLicitacoes) {
        this.participouLicitacoes = participouLicitacoes;
    }

    public String getOrigemContato() {
        return origemContato;
    }

    public void setOrigemContato(String origemContato) {
        this.origemContato = origemContato;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
