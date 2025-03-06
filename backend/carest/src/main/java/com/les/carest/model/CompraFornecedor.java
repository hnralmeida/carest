package com.les.carest.model;

import java.util.Date;

@Entity
public class CompraFornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Fornecedor fornecedor_id;
    private double valor;
    private Date vencimento;
    private String descricao;

    public CompraFornecedor(int id, Fornecedor fornecedor_id, double valor, Date vencimento, String descricao) {
        this.id = id;
        this.fornecedor_id = fornecedor_id;
        this.valor = valor;
        this.vencimento = vencimento;
        this.descricao = descricao;
    }

    public CompraFornecedor(Fornecedor fornecedor_id, double valor, Date vencimento, String descricao) {
        this.fornecedor_id = fornecedor_id;
        this.valor = valor;
        this.vencimento = vencimento;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fornecedor getFornecedor_id() {
        return fornecedor_id;
    }

    public void setFornecedor_id(Fornecedor fornecedor_id) {
        this.fornecedor_id = fornecedor_id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
