package com.les.carest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "produto_serial")
public class ProdutoSerial extends Produto {

    private String codigo;

    public ProdutoSerial() {}

    public ProdutoSerial(String nome, double valor, String codigo) {
        super(nome, valor);
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
