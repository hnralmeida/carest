package com.les.carest.service;

import com.les.carest.model.Produto;
import com.les.carest.repository.ProdutoRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@Tag(name = "ProdutoService", description = "Acesso aos métodos do Produto")
public class ProdutoService extends _GenericService<Produto, ProdutoRepository> {

    protected ProdutoService(ProdutoRepository ProdutoRepository) {
        super(ProdutoRepository);
    }
}
