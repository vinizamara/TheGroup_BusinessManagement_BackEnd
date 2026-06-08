package com.thegroup.business_management_api.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "administradores")
@PrimaryKeyJoinColumn(name = "id_usuario") // une a chave com a tabela pai "usuarios"
public class Administrador extends Usuario {

    // agregação (1 adminstrador para 0..* clientes)
    @OneToMany
    @JoinColumn(name = "admin_id")
    private List<Cliente> clientes = new ArrayList<>();

    public Administrador() {
        super();
    }

    public Administrador(Long idUsuario, String nome, String email, String senha) {
        super(idUsuario, nome, email, senha);
    }

    @Override
    public boolean autenticar(String email, String senha) {
        return true;
    }

    // getters e setters
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
