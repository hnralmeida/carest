package com.les.carest.relatoriosDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class UltimaVendaDTO {
    //talvez usar outro jeito como o model.
    private UUID vendaId;
    private UUID clienteId;
    private LocalDateTime dataVenda;
    private Double valorTotal;

    // Construtor (para facilitar a criação a partir do Object[])
    public UltimaVendaDTO(Object[] resultado) {
        this.vendaId = (UUID) resultado[0];
        this.clienteId = (UUID) resultado[1];
        this.dataVenda = (LocalDateTime) resultado[2];
        this.valorTotal = (Double) resultado[3];
    }

    // Getters e Setters

    public UUID getVendaId() {
        return vendaId;
    }

    public void setVendaId(UUID vendaId) {
        this.vendaId = vendaId;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}