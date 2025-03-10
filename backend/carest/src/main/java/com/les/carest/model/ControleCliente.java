package com.les.carest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;
@Entity
@Table(name = "controle_cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ControleCliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    private Date entrada;
    private Date saida;

}
