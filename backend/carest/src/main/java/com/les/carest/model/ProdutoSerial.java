package com.les.carest.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto_serial")
@DiscriminatorValue("BALANCA")
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
