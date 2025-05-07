package com.les.carest.DTO;

import com.les.carest.model.Cliente;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

public class ClienteDTO {
    private UUID id;
    private String nome;
    private String email;
    private String codigo;
    private double limite;

    private String telefone;
    private int idade;
    private double saldo;
    private Date dataDivida;

    // Construtor completo
    public ClienteDTO(Cliente cliente) {}

    public ClienteDTO(UUID id, String nome, String email, double limite, String telefone,double saldo, Date dataDivida, String codigo) {

        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.saldo = saldo;
        this.dataDivida = dataDivida;
        this.limite = limite;

    }
    public ClienteDTO(UUID id, String nome, String email, String telefone, int idade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.idade = idade;
    }

    public static ClienteDTO fromCliente(Cliente cliente) {
        Date nascimento = cliente.getNascimento();
        LocalDate dataNasc = nascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int idade = LocalDate.now().getYear() - dataNasc.getYear();

        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                idade
        );
    }


    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getDataDivida() {
        return dataDivida;
    }

    public void setDataDivida(Date dataDivida) {
        this.dataDivida = dataDivida;
    }

}
