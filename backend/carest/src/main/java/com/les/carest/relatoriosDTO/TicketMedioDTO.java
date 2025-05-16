package com.les.carest.relatoriosDTO;

import com.les.carest.model.Cliente;

public class TicketMedioDTO {
    private Cliente cliente;
    private Double valor;

    // Construtor, Getters e Setters
    public TicketMedioDTO(Cliente cliente, Double valorMedio) {
        this.cliente = cliente;
        this.valor = valorMedio;
    }

    // Getters e Setters (gerados via IDE ou Lombok, se preferir)
    public Cliente getCliente() { return cliente; }
    public Double getValorMedio() { return valor; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public void setValorMedio(Double valorMedio) { this.valor = valorMedio; }

}