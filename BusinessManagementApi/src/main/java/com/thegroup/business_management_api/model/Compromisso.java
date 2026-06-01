package com.thegroup.business_management_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "compromissos")
public class Compromisso {

    // Identificador único do compromisso
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompromisso;

    // Informações principais do compromisso
    private String titulo;
    private String pregao;
    private String descricao;

    // Data e horário agendados
    private LocalDate data;
    private LocalTime horario;

    // Controle de status e alerta
    private String statusCompromisso;
    private Integer antecedenciaAlerta;

    // Tipo associado ao compromisso
    @ManyToOne
    @JoinColumn(name = "id_tipo_compromisso")
    private TipoCompromisso tipoCompromisso;

    // Agenda à qual o compromisso pertence
    @ManyToOne
    @JoinColumn(name = "id_agenda")
    @JsonBackReference
    private Agenda agenda;

    // Construtor padrão exigido pelo JPA
    public Compromisso() {
    }

    // Regras de negócio da entidade
    public void marcarComoConcluido() {
        this.statusCompromisso = "Concluído";
    }

    public boolean verificarProximidade() {
        LocalDate hoje = LocalDate.now();
        LocalDate dataAlerta = data.minusDays(antecedenciaAlerta);

        return !hoje.isBefore(dataAlerta)
                && !hoje.isAfter(data);
    }

    public String gerarAlerta() {
        if (verificarProximidade()) {
            return "Alerta: o compromisso \"" + titulo + "\" está próximo.";
        }

        return "Não há alerta para este compromisso no momento.";
    }

    // Getters e Setters
    public Long getIdCompromisso() {
        return idCompromisso;
    }

    public void setIdCompromisso(Long idCompromisso) {
        this.idCompromisso = idCompromisso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPregao() {
        return pregao;
    }

    public void setPregao(String pregao) {
        this.pregao = pregao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getStatusCompromisso() {
        return statusCompromisso;
    }

    public void setStatusCompromisso(String statusCompromisso) {
        this.statusCompromisso = statusCompromisso;
    }

    public Integer getAntecedenciaAlerta() {
        return antecedenciaAlerta;
    }

    public void setAntecedenciaAlerta(Integer antecedenciaAlerta) {
        this.antecedenciaAlerta = antecedenciaAlerta;
    }

    public TipoCompromisso getTipoCompromisso() {
        return tipoCompromisso;
    }

    public void setTipoCompromisso(TipoCompromisso tipoCompromisso) {
        this.tipoCompromisso = tipoCompromisso;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
}