package com.les.carest.model;

import java.util.List;
@Entity
public class Tela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    @OneToMany(mappedBy = "tela")
    private List<Permissao> permissoes;

    public Tela(int id, String nome, List<Permissao> permissoes) {
        this.id = id;
        this.nome = nome;
        this.permissoes = permissoes;
    }

    public Tela(String nome, List<Permissao> permissoes) {
        this.nome = nome;
        this.permissoes = permissoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
}
