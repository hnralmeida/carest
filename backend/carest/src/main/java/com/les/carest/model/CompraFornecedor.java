package com.les.carest.model;

import java.util.Date;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "compra_fornecedor")
public class CompraFornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    private double valor;
    private Date vencimento;
    private String descricao;

    public CompraFornecedor(UUID id, Fornecedor fornecedor, double valor, Date vencimento, String descricao) {
        this.id = id;
        this.fornecedor = fornecedor;
        this.valor = valor;
        this.vencimento = vencimento;
        this.descricao = descricao;
    }

    public CompraFornecedor(Fornecedor fornecedor, double valor, Date vencimento, String descricao) {
        this.fornecedor = fornecedor;
        this.valor = valor;
        this.vencimento = vencimento;
        this.descricao = descricao;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setfornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
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