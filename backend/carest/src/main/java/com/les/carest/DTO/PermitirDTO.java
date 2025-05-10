package com.les.carest.DTO;

import java.util.UUID;

public class PermitirDTO {
    private UUID userId;
    private String nomeTela;
    private String rotaTela;

    public String getRotaTela() {
        return rotaTela;
    }

    public void setRotaTela(String rotaTela) {
        this.rotaTela = rotaTela;
    }

    // Getters e Setters
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getNomeTela() {
        return nomeTela;
    }

    public void setNomeTela(String nomeTela) {
        this.nomeTela = nomeTela;
    }
}
