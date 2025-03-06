package com.les.carest.model;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    protected String nome;
    protected   double valor;
    @OneToMany(mappedBy = "produto")
    private List<Venda> vendas;

    public Produto(int id, String nome, double valor, List<Venda> vendas) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.vendas = vendas;
    }

    public Produto(String nome, double valor, List<Venda> vendas) {
        this.nome = nome;
        this.valor = valor;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
}
