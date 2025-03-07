package com.les.carest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "produto_serial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoSerial extends Produto {

    private int codigo;
}
