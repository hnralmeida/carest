package com.les.carest.controller;

import com.les.carest.model.Produto;
import com.les.carest.service.ProdutoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/produto")
public class ProdutoController extends _GenericController<Produto> {
    public ProdutoController(ProdutoService produtoService) {
        super(produtoService);
    }

}