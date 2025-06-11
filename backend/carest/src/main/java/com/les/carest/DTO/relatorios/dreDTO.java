package com.les.carest.DTO.relatorios;

public class dreDTO {

    private String dia;

    @PdfFormat(numberPattern = "R$ #,##0.00")
    private Double receber;
    @PdfFormat(numberPattern = "R$ #,##0.00")
    private Double pagar;
    @PdfFormat(numberPattern = "R$ #,##0.00")
    private Double resultado;
    @PdfFormat(numberPattern = "R$ #,##0.00")
    private Double saldo;


    public dreDTO(String dia, Double receber, Double pagar, Double resultado, Double saldo) {
        this.dia = dia;
        this.receber = receber;
        this.pagar = pagar;
        this.resultado = resultado;
        this.saldo = saldo;
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

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
