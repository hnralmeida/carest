package com.les.carest.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tela")
public class Tela {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String rota;

    public Tela(UUID id, String nome, String rota) {
        this.id = id;
        this.nome = nome;
        this.rota = rota;
    }

    public Tela() {

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

    public String getRota() {
        return rota;
    }

    public void setRota(String rota) {
        this.rota = rota;
    }

}
