package com.les.carest.DTO;

import java.util.UUID;

public class ProdutoDTO_Serial {
    private UUID id;
    private String nome;
    private double valor;
    private int codigo;

    public ProdutoDTO_Serial(UUID id, String nome, double valor, int codigo) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.codigo = codigo;
    }

    // Getters e Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }
}