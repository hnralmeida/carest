package com.les.carest.service;

import com.les.carest.model.ProdutoSerial;
import com.les.carest.repository.ProdutoSerialRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@Tag(name = "ProdutoSerialService", description = "Acesso aos métodos de Produto Serial")
public class ProdutoSerialService extends _GenericService<ProdutoSerial, ProdutoSerialRepository> {

    protected void ProdutoSerialRepository(ProdutoSerialRepository ProdutoSerialRepository) {
        super(ProdutoSerialRepository);
    }
}
