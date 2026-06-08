package com.thegroup.business_management_api.model;

// define as regras que outras classes devem seguir
public interface Exportavel {

    // quem implementar essa interface é obrigado a ter esse metodo e ele devolve um texto
    String exportarDados();
}
