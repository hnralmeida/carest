package com.les.carest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "recarga")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recarga {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "imagem_id")
    private Cliente cliente;

    private Date data;
    private double valor;

}
