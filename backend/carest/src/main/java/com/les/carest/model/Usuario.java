package com.les.carest.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
     @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private long codigo;
    @OneToMany(mappedBy = "usuario")
    private List<Permissao> permissoes;
}
