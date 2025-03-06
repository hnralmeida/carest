package org.model;

import java.util.Date;

@Entity
public class ControleCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Cliente cliente_id;
    private Date entrada;
    private Date saida;

    public ControleCliente(int id, Cliente cliente_id, Date entrada, Date saida) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.entrada = entrada;
        this.saida = saida;
    }

    public ControleCliente(Cliente cliente_id, Date entrada, Date saida) {
        this.cliente_id = cliente_id;
        this.entrada = entrada;
        this.saida = saida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Cliente cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }
}
