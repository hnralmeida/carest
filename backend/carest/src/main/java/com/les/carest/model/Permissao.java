package com.les.carest.model;

import com.probuild.backend.domain.interfaces.Pronomes;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "permissao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Permissao {
    @ManyToOne
    private Usuario usuario_id;
    @ManyToOne
    private Tela tela_id;
    private boolean create;
    private boolean read;
    private boolean update;
    private boolean delete;
}
