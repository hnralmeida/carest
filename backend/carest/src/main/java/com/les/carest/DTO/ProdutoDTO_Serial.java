package com.les.carest.DTO;

import java.util.UUID;

public class ProdutoDTO_Serial {
    private UUID id;
    private String nome;
    private double valor;
    private String codigo; // Campo específico do Serial

    // Construtor vazio
    public ProdutoDTO_Serial() {}

    // Getters e Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }  
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
}