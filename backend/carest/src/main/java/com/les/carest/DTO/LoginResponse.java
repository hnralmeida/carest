package com.les.carest.DTO;

import com.les.carest.model.Permissao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LoginResponse {
    private UUID id;
    private String nome;
    private String email;
    private List<Permissao> permissoes = new ArrayList<>();

    public LoginResponse(UUID id, String nome, String email, List<Permissao> permissoes) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.permissoes = permissoes;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
