package com.les.carest.DTO.relatorios;

import java.util.Date;

public class DesempenhoDTO {
    private Date data;
    private double entrada;
    private double saida;
    private long clientes;
    private double saldoAnterior;

    public DesempenhoDTO(Date data, double entrada, double saida, long clientes, double saldoAnterior) {
        this.data = data;
        this.entrada = entrada;
        this.saida = saida;
        this.clientes = clientes;
        this.saldoAnterior = saldoAnterior;
    }

    // Getters e Setters

    public double getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(double saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getEntrada() {
        return entrada;
    }

    public void setEntrada(double entrada) {
        this.entrada = entrada;
    }

    public double getSaida() {
        return saida;
    }

    public void setSaida(double saida) {
        this.saida = saida;
    }

    public long getClientes() {
        return clientes;
    }

    public void setClientes(long clientes) {
        this.clientes = clientes;
    }
}
