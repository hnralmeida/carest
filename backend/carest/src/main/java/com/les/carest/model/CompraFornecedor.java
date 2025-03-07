package com.les.carest.model;

import java.util.Date;
import com.probuild.backend.domain.interfaces.Pronomes;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;


@Entity
@Table(name = "compra_fornecedor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompraFornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private Fornecedor fornecedor_id;
    private double valor;
    private Date vencimento;
    private String descricao;
}
