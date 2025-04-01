package com.les.carest.DTO;

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
    private int idade;

    // Construtor completo
    public AniversarianteDTO(UUID id, String nome, String email, String telefone, int idade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.idade = idade;
    }

    public static AniversarianteDTO fromCliente(Cliente cliente) {
        Date nascimento = cliente.getNascimento();
        LocalDate dataNasc = nascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int idade = LocalDate.now().getYear() - dataNasc.getYear();

        return new AniversarianteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                idade
        );
    }
}
