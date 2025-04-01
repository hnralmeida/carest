package com.les.carest.DTO;

import java.util.UUID;

public class AtribuicaoPermissaoDTO {
    private UUID usuarioAlvoId;
    private UUID telaId;
    private boolean create;
    private boolean read;
    private boolean update;
    private boolean delete;

    public UUID getUsuarioAlvoId() {
        return usuarioAlvoId;
    }

    public void setUsuarioAlvoId(UUID usuarioAlvoId) {
        this.usuarioAlvoId = usuarioAlvoId;
    }

    public UUID getTelaId() {
        return telaId;
    }

    public void setTelaId(UUID telaId) {
        this.telaId = telaId;
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