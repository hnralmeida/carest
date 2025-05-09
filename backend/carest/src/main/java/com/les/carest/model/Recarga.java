package com.les.carest.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "recarga")
public class Recarga {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private Date data;
    private double valor;

    public Recarga() {}

    public Recarga(UUID id, Date data, Cliente cliente, double valor) {
        this.id = id;
        this.data = data;
        this.cliente = cliente;
        this.valor = valor;
    }

    public Recarga(Cliente cliente, Date data, double valor) {
        this.cliente = cliente;
        this.data = data;
        this.valor = valor;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
