package org.model;

import java.util.Date;
import java.util.List;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private Date nascimento;
    private String telefone;
    private String email;
    private double limite;
    private double saldo;
    private boolean em_uso;
    private Date dividaData;
    @OneToMany(mappedBy = "cliente")
    private List<Recarga> recargas;
    @OneToMany(mappedBy = "cliente")
    private List<ControleCliente> controles;
    @OneToMany(mappedBy = "cliente")
    private List<Venda> vendas;

    public Cliente(int id, String nome, Date nascimento, String telefone, String email, double limite, double saldo, boolean em_uso, Date dividaData, List<Recarga> recargas, List<ControleCliente> controles, List<Venda> vendas) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.email = email;
        this.limite = limite;
        this.saldo = saldo;
        this.em_uso = em_uso;
        this.dividaData = dividaData;
        this.recargas = recargas;
        this.controles = controles;
        this.vendas = vendas;
    }

    public Cliente(String nome, Date nascimento, String telefone, String email, double limite, double saldo, boolean em_uso, Date dividaData, List<Recarga> recargas, List<ControleCliente> controles, List<Venda> vendas) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.email = email;
        this.limite = limite;
        this.saldo = saldo;
        this.em_uso = em_uso;
        this.dividaData = dividaData;
        this.recargas = recargas;
        this.controles = controles;
        this.vendas = vendas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isEm_uso() {
        return em_uso;
    }

    public void setEm_uso(boolean em_uso) {
        this.em_uso = em_uso;
    }

    public Date getDividaData() {
        return dividaData;
    }

    public void setDividaData(Date dividaData) {
        this.dividaData = dividaData;
    }

    public List<Recarga> getRecargas() {
        return recargas;
    }

    public void setRecargas(List<Recarga> recargas) {
        this.recargas = recargas;
    }

    public List<ControleCliente> getControles() {
        return controles;
    }

    public void setControles(List<ControleCliente> controles) {
        this.controles = controles;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
}
