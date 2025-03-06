package org.model;

import java.util.List;

@Entity
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    @OneToMany(mappedBy = "fornecedor")
    private List<CompraFornecedor> compras;

    public Fornecedor(int id, String nome, List<CompraFornecedor> compras) {
        this.id = id;
        this.nome = nome;
        this.compras = compras;
    }

    public Fornecedor(String nome, List<CompraFornecedor> compras) {
        this.nome = nome;
        this.compras = compras;
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

    public List<CompraFornecedor> getCompras() {
        return compras;
    }

    public void setCompras(List<CompraFornecedor> compras) {
        this.compras = compras;
    }
}
