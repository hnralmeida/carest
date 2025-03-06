package com.les.carest.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PRODUTO SERIAL") // Define o valor do campo "tipo" na tabela
public class ProdutoSerial extends Produto {

    private int codigo;

    public ProdutoSerial() {
    }

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
