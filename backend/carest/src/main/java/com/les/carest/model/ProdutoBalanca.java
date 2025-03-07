package com.les.carest.model;

import jakarta.persistence.*;
import java.util.Date;

import com.probuild.backend.domain.interfaces.Pronomes;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
    

@Entity
@DiscriminatorValue("PRODUTO BALANÇA") // Define o valor no campo "tipo"
public class ProdutoBalanca extends Produto {

    @Temporal(TemporalType.TIMESTAMPBalanca(S))
    private Date data;

}
