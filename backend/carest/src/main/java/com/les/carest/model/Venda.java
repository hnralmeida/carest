package com.les.carest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "venda")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private Date dataVenda;
    private int valor;

    public Venda(UUID id, Produto produto, Cliente cliente, Date dataVenda, int valor) {
        this.id = id;
        this.produto = produto;
        this.cliente = cliente;
        this.dataVenda = dataVenda;
        this.valor = valor;
    }

    public Venda(Produto produto, Cliente cliente, Date dataVenda, int valor) {
        this.produto = produto;
        this.cliente = cliente;
        this.dataVenda = dataVenda;
        this.valor = valor;
    }

    public Venda() {
        
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
