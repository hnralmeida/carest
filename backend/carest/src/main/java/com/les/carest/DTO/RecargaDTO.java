package com.les.carest.DTO;

import java.util.Date;
import java.util.UUID;

public class RecargaDTO {
    private UUID idCliente;
    private double valorRecarga;
    private Date dataRecarga;


    public RecargaDTO() {}

    public RecargaDTO(UUID idCliente, double valorRecarga, Date dataRecarga) {
        this.idCliente = idCliente;
        this.valorRecarga = valorRecarga;
        this.dataRecarga = new Date();
    }


    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public double getValorRecarga() {
        return valorRecarga;
    }

    public void setValorRecarga(double valorRecarga) {
        this.valorRecarga = valorRecarga;
    }

    public Date getDataRecarga() {
        return dataRecarga;
    }

    public void setDataRecarga(Date dataRecarga) {
        this.dataRecarga = dataRecarga;
    }
}