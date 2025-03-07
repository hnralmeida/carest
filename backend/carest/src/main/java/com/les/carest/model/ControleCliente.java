package com.les.carest.model;

import com.probuild.backend.domain.interfaces.Pronomes;
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
    private Cliente cliente_id;
    private Date entrada;
    private Date saida;

}
