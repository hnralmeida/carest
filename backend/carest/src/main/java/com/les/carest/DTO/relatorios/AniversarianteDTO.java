package com.les.carest.DTO.relatorios;

import com.les.carest.model.Cliente;

import java.time.LocalDate;
import java.time.ZoneId;

public class AniversarianteDTO {

    private String nome;
    @PdfFormat(datePattern = "dd/MM/yyyy", nullValue = "Sem data")
    private LocalDate nascimento;
    @PdfFormat(numberPattern = "# anos")
    private Integer idade;

    // Construtor a partir da entidade Cliente (com cálculo da idade)
    public AniversarianteDTO(Cliente cliente) {
        this.nome = cliente.getNome();
        this.nascimento = cliente.getNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.idade = calcularIdade(cliente.getNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    // Método para calcular idade (similar ao ClienteDTO)
    private int calcularIdade(LocalDate dataNasc) {
        return LocalDate.now().getYear() - dataNasc.getYear();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}