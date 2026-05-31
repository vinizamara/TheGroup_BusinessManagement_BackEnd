package com.thegroup.business_management_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tipos_compromisso")
public class TipoCompromisso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoCompromisso;

    private String nome;
    private String descricao;

    // construtor padrão
    public TipoCompromisso() {
    }

    // getters e setters
    public Long getIdTipoCompromisso() {
        return idTipoCompromisso;
    }

    public void setIdTipoCompromisso(Long idTipoCompromisso) {
        this.idTipoCompromisso = idTipoCompromisso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
