package com.les.carest.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto_serial")
@DiscriminatorValue("BALANCA")
public class ProdutoSerial extends Produto {

    @Column(nullable = true, length = 100)
    private String codigo;
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
