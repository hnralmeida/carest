package com.les.carest.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "acesso")
public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;
    private Date entrada;
    private Date saida;

    public Acesso(Cliente cliente, Date entrada) {
        this.cliente = cliente;
        this.entrada = entrada;
    }

    public Acesso() {}


    public Acesso(UUID id, Cliente cliente, Date entrada, Date saida) {
        this.id = id;
        this.cliente = cliente;
        this.entrada = entrada;
        this.saida = saida;
    }

    public Acesso(Cliente cliente, Date entrada, Date saida) {
        this.cliente = cliente;
        this.entrada = entrada;
        this.saida = saida;
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

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

}
