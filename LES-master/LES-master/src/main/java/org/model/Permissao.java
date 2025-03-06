package org.model;

@Entity
public class Permissao {
    @ManyToOne
    private Usuario usuario_id;
    @ManyToOne
    private Tela tela_id;
    private boolean create;
    private boolean read;
    private boolean update;
    private boolean delete;

    public Permissao(Usuario usuario_id, Tela tela_id, boolean create, boolean read, boolean update, boolean delete) {
        this.usuario_id = usuario_id;
        this.tela_id = tela_id;
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

    public Usuario getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Usuario usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Tela getTela_id() {
        return tela_id;
    }

    public void setTela_id(Tela tela_id) {
        this.tela_id = tela_id;
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
