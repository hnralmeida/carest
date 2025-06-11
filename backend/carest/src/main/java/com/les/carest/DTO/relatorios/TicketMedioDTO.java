package com.les.carest.DTO.relatorios;

import com.les.carest.model.Cliente;


public class TicketMedioDTO {
    private String cliente;

    @PdfFormat(numberPattern = "R$ #,##0.00")
    private Double ticket_medio;

    // Construtor, Getters e Setters
    public TicketMedioDTO(Cliente cliente, Double valorMedio) {
        this.cliente = cliente.getNome();
        this.ticket_medio = valorMedio;
    }


    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getTicket_medio() {
        return ticket_medio;
    }

    public void setTicket_medio(Double ticket_medio) {
        this.ticket_medio = ticket_medio;
    }
}