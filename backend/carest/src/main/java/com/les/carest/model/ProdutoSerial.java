package com.les.carest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "produto_serial")
public class ProdutoSerial extends Produto {

    private int codigo;

    public ProdutoSerial() {}

    public ProdutoSerial(String nome, double valor, int codigo) {
        super(nome, valor);
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
