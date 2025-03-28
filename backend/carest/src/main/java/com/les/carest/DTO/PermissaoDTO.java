package com.les.carest.DTO;

import com.les.carest.model.Permissao;

import java.util.UUID;

public class PermissaoDTO {
    private UUID id;
    private String nome;
    private boolean create;
    private boolean read;
    private boolean update;
    private boolean delete;

    public PermissaoDTO(Permissao permissao) {
        this.nome = permissao.getTela().getNome(); // Ajuste conforme necessário
    }

    public PermissaoDTO(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTelaNome() {
        return nome;
    }

    public void setTelaNome(String nome) {
        this.nome = nome;
    }

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
