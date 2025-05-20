package com.les.carest.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;


@Entity
@Table(name = "produto_balanca")
@DiscriminatorValue("SERIAL")
public class ProdutoBalanca extends Produto {

    private Date data;

    public ProdutoBalanca (String nome, double valor, Date data) {
        super(nome, valor);
        this.data = data;
    }

    public ProdutoBalanca() {};

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
