package com.les.carest.model;

import jakarta.persistence.*;
import java.util.Date;

import com.probuild.backend.domain.interfaces.Pronomes;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
    

@Entity
@Table(name = "produto_balanca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoBalanca extends Produto {

    @Temporal(TemporalType.TIMESTAMPBalanca(S))
    private Date data;

}
