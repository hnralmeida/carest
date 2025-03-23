package com.les.carest.model;

import jakarta.persistence.*;
import java.util.Date;


@Entity
@Table(name = "produto_balanca")
public class ProdutoBalanca extends Produto {

    private Date data;

    public ProdutoBalanca() {
        super();
    }

    public ProdutoBalanca(String nome, double valor, Date data) {
        super(nome, valor);
        this.data = data;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
