package com.les.carest.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "permissao")
public class Permissao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tela_id")
    private Tela tela;

    private boolean create;
    private boolean read;
    private boolean update;
    private boolean delete;

    public Permissao(Usuario usuario, Tela tela, boolean create, boolean read, boolean update, boolean delete) {
        this.usuario = usuario;
        this.tela = tela;
        this.create = create;
        this.read = read;
        this.update = update;
        this.delete = delete;
    }

    public Permissao(boolean read, boolean create, boolean update, boolean delete) {
        this.read = read;
        this.create = create;
        this.update = update;
        this.delete = delete;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tela getTela() {
        return tela;
    }

    public void setTela(Tela tela) {
        this.tela = tela;
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
