package com.les.carest.DTO;

import com.les.carest.model.Cliente;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

public class AniversarianteDTO {
    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private int idade;
    private LocalDate dataNascimento;

    public AniversarianteDTO() {}

    public AniversarianteDTO(UUID id, String nome, String email, String telefone, int idade, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.idade = idade;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters
    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public int getIdade() { return idade; }
    public LocalDate getDataNascimento() { return dataNascimento; }

    public void setId(UUID id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setIdade(int idade) { this.idade = idade; }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public static AniversarianteDTO fromCliente(Cliente cliente) {
        Date nascimento = cliente.getNascimento();
        LocalDate dataNasc = nascimento.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        int idade = Period.between(dataNasc, LocalDate.now()).getYears();

        return new AniversarianteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                idade,
                dataNasc  // Passando a data de nascimento completa
        );
    }
}