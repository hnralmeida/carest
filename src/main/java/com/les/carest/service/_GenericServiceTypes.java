package com.les.carest.service;

import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.UUID;

public interface _GenericServiceTypes<Tipo> {

    List<Tipo> listar();

    Tipo buscarPorId(UUID id);

    Tipo criar(Tipo entity);

    Tipo atualizar(@Positive UUID id, Tipo entity);

    void excluir(UUID id);

}