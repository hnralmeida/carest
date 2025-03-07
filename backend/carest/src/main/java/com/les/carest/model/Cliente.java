package com.les.carest.model;

import jakarta.persistence.Entity;

import java.util.Date;
import java.util.List;
import com.probuild.backend.domain.interfaces.Pronomes;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
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

}
