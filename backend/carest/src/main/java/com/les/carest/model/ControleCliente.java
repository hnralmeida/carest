package com.les.carest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;
@Entity
@Table(name = "controle_cliente")
public class ControleCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    private Date entrada;
    private Date saida;

    public ControleCliente(UUID id, Cliente cliente, Date entrada, Date saida) {
        this.id = id;
        this.cliente = cliente;
        this.entrada = entrada;
        this.saida = saida;
    }

    public ControleCliente(Cliente cliente, Date entrada, Date saida) {
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

    public Cliente getcliente() {
        return cliente;
    }

    public void setCliente_id(Cliente cliente) {
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