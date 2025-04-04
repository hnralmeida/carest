package com.les.carest.DTO;

import java.util.Date;
import java.util.UUID;

public class ProdutoDTO_Balanca {
    private UUID id;
    private double valor;
    private Date data;// Supondo que só tenha esses campos

    // Construtor
    public ProdutoDTO_Balanca(UUID id, double valor) {
        this.id = id;
        this.valor = valor;
    }


    // Construtor vazio
    public ProdutoDTO_Balanca() {
    }

    // Getters e Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}



