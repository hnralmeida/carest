package com.les.carest.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tela")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tela {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    @OneToMany(mappedBy = "tela")
    private List<Permissao> permissoes;
}
