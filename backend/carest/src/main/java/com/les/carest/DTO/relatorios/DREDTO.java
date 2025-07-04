package com.les.carest.DTO.relatorios;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para relatório de desempenho de vendas em um período")
public class DREDTO {

    @Schema(description = "Data", example = "01/07/2025")
    @PdfFormat(datePattern = "dd/MM/yyyy")
    private String dia;

    @PdfFormat(numberPattern = "R$ #,##0.00")
    private Double receber;

    @PdfFormat(numberPattern = "R$ #,##0.00")
    private Double pagar;

    @PdfFormat(numberPattern = "R$ #,##0.00")
    private Double resultado;

    @PdfFormat(numberPattern = "R$ #,##0.00")
    private Double saldoAnterior;


    public DREDTO(String dia, Double receber, Double pagar, Double resultado, Double saldoAnterior) {
        this.dia = dia;
        this.receber = receber;
        this.pagar = pagar;
        this.resultado = resultado;
        this.saldoAnterior = saldoAnterior;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Double getReceber() {
        return receber;
    }

    public void setReceber(Double receber) {
        this.receber = receber;
    }

    public Double getPagar() {
        return pagar;
    }

    public void setPagar(Double pagar) {
        this.pagar = pagar;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public Double getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(Double saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }
}
