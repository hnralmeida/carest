package com.les.carest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;

    @Column(unique = true)
    private String codigo;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date nascimento;
    private String telefone;
    private String email;
    private double limite;
    private double saldo;
    private boolean em_uso;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date dividaData;
    private boolean bloqueado;

    public Cliente(){}


    public Cliente(UUID id, String nome, String codigo, Date nascimento, String telefone, String email, double limite, double saldo, boolean em_uso, Date dividaData, boolean bloqueado) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.email = email;
        this.limite = limite;
        this.saldo = saldo;
        this.em_uso = em_uso;
        this.dividaData = dividaData;
        this.bloqueado = bloqueado;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public boolean getBloqueado() {return bloqueado;    }

    public void setBloqueado(boolean bloqueado) { this.bloqueado = bloqueado; }


}
