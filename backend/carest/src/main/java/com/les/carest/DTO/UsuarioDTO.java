package com.les.carest.DTO;

import com.les.carest.model.Usuario;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UsuarioDTO {
    private UUID id;
    private String nome;
    private String email;
    private List<PermissaoDTO> permissoes;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.permissoes = usuario.getPermissoes().stream()
                .map(permissao -> new PermissaoDTO(permissao)) // Lambda explícito
                .collect(Collectors.toList());
    }

    // Getters e Setters

    public List<PermissaoDTO> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<PermissaoDTO> permissoes) {
        this.permissoes = permissoes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
