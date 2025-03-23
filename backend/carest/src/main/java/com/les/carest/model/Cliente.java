package com.les.carest.model;

import jakarta.persistence.Entity;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private Date nascimento;
    private String telefone;
    private String email;
    private double limite;
    private double saldo;
    private boolean em_uso;
    private Date dividaData;

     public Cliente(UUID id, String nome, Date nascimento, String telefone, String email, double limite, double saldo, boolean em_uso, Date dividaData) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.email = email;
        this.limite = limite;
        this.saldo = saldo;
        this.em_uso = em_uso;
        this.dividaData = dividaData;
    }

    public Cliente(String nome, Date nascimento, String telefone, String email, double limite, double saldo, boolean em_uso, Date dividaData) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.email = email;
        this.limite = limite;
        this.saldo = saldo;
        this.em_uso = em_uso;
        this.dividaData = dividaData;
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

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isEm_uso() {
        return em_uso;
    }

    public void setEm_uso(boolean em_uso) {
        this.em_uso = em_uso;
    }

    public Date getDividaData() {
        return dividaData;
    }

    public void setDividaData(Date dividaData) {
        this.dividaData = dividaData;
    }
}
