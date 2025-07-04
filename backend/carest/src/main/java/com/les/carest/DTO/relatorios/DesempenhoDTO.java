package com.les.carest.DTO.relatorios;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(description = "DTO para relatório de desempenho de vendas em um período")
public class DesempenhoDTO {

    @Schema(description = "Data", example = "01/07/2025")
    @PdfFormat(datePattern = "mm/dd/yyyy")
    private Date data;

    @Schema(description = "Entrada")
    @PdfFormat(numberPattern = "R$ #,##0.00")
    private double entrada;

    @Schema(description = "Saída")
    @PdfFormat(numberPattern = "R$ #,##0.00")
    private double saida;

    @Schema(description = "Clientes")
    private long clientes;

    @Schema(description = "Saldo Anterior")
    @PdfFormat(numberPattern = "R$ #,##0.00")
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
