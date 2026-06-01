package com.thegroup.business_management_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agendas")
public class Agenda {

    // Identificador único da agenda
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgenda;

    // Informações básicas da agenda
    private String nome;

    private LocalDate dataCriacao;

    // Compromissos associados à agenda
    @OneToMany(mappedBy = "agenda")
    @JsonManagedReference
    private List<Compromisso> compromissos = new ArrayList<>();

    // Construtor padrão exigido pelo JPA
    public Agenda() {
        this.dataCriacao = LocalDate.now();
    }

    // Retorna apenas os compromissos pendentes
    public List<Compromisso> listarPendentes() {

        List<Compromisso> pendentes = new ArrayList<>();

        for (Compromisso compromisso : compromissos) {

            if ("Pendente".equalsIgnoreCase(
                    compromisso.getStatusCompromisso())) {

                pendentes.add(compromisso);
            }
        }

        return pendentes;
    }

    // Getters e Setters

    public Long getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Long idAgenda) {
        this.idAgenda = idAgenda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<Compromisso> getCompromissos() {
        return compromissos;
    }

    public void setCompromissos(List<Compromisso> compromissos) {
        this.compromissos = compromissos;
    }
}