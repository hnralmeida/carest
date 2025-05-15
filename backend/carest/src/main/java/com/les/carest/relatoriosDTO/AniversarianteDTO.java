package com.les.carest.relatoriosDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.les.carest.model.Cliente;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

public class AniversarianteDTO {
    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date nascimento;
    private int idade;

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    // Construtor a partir da entidade Cliente (com cálculo da idade)
    public AniversarianteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone();
        this.nascimento = cliente.getNascimento();
        this.idade = calcularIdade(cliente.getNascimento());
    }

    // Método para calcular idade (similar ao ClienteDTO)
    private int calcularIdade(Date nascimento) {
        LocalDate dataNasc = nascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return LocalDate.now().getYear() - dataNasc.getYear();
    }

    // Getters (não incluo setters pois o DTO é imutável após construção)
    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public int getIdade() {
        return idade;
    }
}