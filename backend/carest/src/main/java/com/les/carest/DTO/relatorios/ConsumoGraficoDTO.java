package com.les.carest.DTO.relatorios;

public class ConsumoGraficoDTO {
    private String cliente;
    private Double consumo;

    public ConsumoGraficoDTO(String cliente, Double consumo) {
        this.cliente = cliente;
        this.consumo = consumo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getConsumo() {
        return consumo;
    }

    public void setConsumo(Double consumo) {
        this.consumo = consumo;
    }
}