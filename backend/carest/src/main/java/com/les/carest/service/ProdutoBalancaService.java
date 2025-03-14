package com.les.carest.service;

import com.les.carest.model.ProdutoBalanca;
import com.les.carest.repository.ProdutoBalancaRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@Tag(name = "ProdutoBalancaService", description = "Acesso aos métodos de Produto Balanca")
public class ProdutoBalancaService extends _GenericService<ProdutoBalanca, ProdutoBalancaRepository> {

    protected ProdutoBalancaService(ProdutoBalancaRepository ProdutoBalancaRepository) {
        super(ProdutoBalancaRepository);
    }
}
