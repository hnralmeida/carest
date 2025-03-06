package com.les.carest.model;

import java.util.Date;

@Entity
public class Recarga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Cliente cliente_id;
    private Date data;
    private double valor;

    public Recarga(int id, Date data, Cliente cliente_id, double valor) {
        this.id = id;
        this.data = data;
        this.cliente_id = cliente_id;
        this.valor = valor;
    }

    public Recarga(Cliente cliente_id, Date data, double valor) {
        this.cliente_id = cliente_id;
        this.data = data;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Cliente id_cliente) {
        this.cliente_id = id_cliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
