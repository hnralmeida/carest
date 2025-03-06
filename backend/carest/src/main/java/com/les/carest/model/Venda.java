package com.les.carest.model;

import java.util.Date;

@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Produto produto_id;
    @ManyToOne
    private Cliente cliente_id;
    private Date dataVenda;
    private int valor;

    public Venda(int id, Produto produto_id, Cliente cliente_id, Date dataVenda, int valor) {
        this.id = id;
        this.produto_id = produto_id;
        this.cliente_id = cliente_id;
        this.dataVenda = dataVenda;
        this.valor = valor;
    }

    public Venda(Produto produto_id, Cliente cliente_id, Date dataVenda, int valor) {
        this.produto_id = produto_id;
        this.cliente_id = cliente_id;
        this.dataVenda = dataVenda;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Produto produto_id) {
        this.produto_id = produto_id;
    }

    public Cliente getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Cliente cliente_id) {
        this.cliente_id = cliente_id;
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
