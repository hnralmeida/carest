package com.les.carest.DTO;

public class MesDTO {
    private int mes;

    // Construtor padrão (obrigatório para desserialização JSON)
    public MesDTO() {
    }

    // Getters e Setters
    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
}
