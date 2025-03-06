package org.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("PRODUTO BALANÇA") // Define o valor no campo "tipo"
public class ProdutoBalanca extends Produto {

    @Temporal(TemporalType.TIMESTAMPBalanca(S)
    private Date data;

    public ProdutoBalanca() {
    }

    public Produtotring nome, double valor, Date data) {
        super(nome, valor);
        this.data = data;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
