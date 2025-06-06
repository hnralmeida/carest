package com.les.carest.DTO.relatorios;

import com.les.carest.model.Cliente;

import java.time.LocalDate;

public class DividaDTO {

    private String nome;
    @PdfFormat(numberPattern = "R$ #,##0.00")
    private double saldo_negativo;
    @PdfFormat(numberPattern = "R$ #,##0.00")
    private double limite;

    @PdfFormat()
    private LocalDate data_negativado;

    public DividaDTO(Cliente cliente) {
        this.nome = cliente.getNome();
        this.saldo_negativo = cliente.getSaldo();
        this.limite = cliente.getLimite();
        //this.data_negativado = cliente.getUltimo_dia_negativado().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo_negativo() {
        return saldo_negativo;
    }

    public void setSaldo_negativo(double saldo_negativo) {
        this.saldo_negativo = saldo_negativo;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public LocalDate getData_negativado() {
        return data_negativado;
    }

    public void setData_negativado(LocalDate data_negativado) {
        this.data_negativado = data_negativado;
    }
}