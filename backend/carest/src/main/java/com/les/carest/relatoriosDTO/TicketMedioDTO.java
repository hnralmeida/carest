package com.les.carest.relatoriosDTO;

import java.util.UUID;

public class TicketMedioDTO {
    private UUID clienteId;
    private Double valorMedio;

    // Construtor, Getters e Setters
    public TicketMedioDTO(UUID clienteId, Double valorMedio) {
        this.clienteId = clienteId;
        this.valorMedio = valorMedio;
    }

    // Getters e Setters (gerados via IDE ou Lombok, se preferir)
    public UUID getClienteId() { return clienteId; }
    public Double getValorMedio() { return valorMedio; }
    public void setClienteId(UUID clienteId) { this.clienteId = clienteId; }
    public void setValorMedio(Double valorMedio) { this.valorMedio = valorMedio; }

}