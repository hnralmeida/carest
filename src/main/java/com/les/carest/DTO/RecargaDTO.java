package com.les.carest.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RecargaDTO {
    private String codigoCliente;
    private double valorRecarga;

    // Construtor padrão OBRIGATÓRIO
    public RecargaDTO() {}

    // Construtor com parâmetros (opcional)
    @JsonCreator
    public RecargaDTO(
            @JsonProperty("codigoCliente") String codigoCliente,
            @JsonProperty("valorRecarga") double valorRecarga) {
        this.codigoCliente = codigoCliente;
        this.valorRecarga = valorRecarga;
    }

    // Getters e Setters OBRIGATÓRIOS
    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public double getValorRecarga() {
        return valorRecarga;
    }

    public void setValorRecarga(double valorRecarga) {
        this.valorRecarga = valorRecarga;
    }
}