package com.les.carest.controller;

import com.les.carest.model.Fornecedor;
import com.les.carest.service.FornecedorService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@RequestMapping("/fornecedor")
public class FornecedorController extends _GenericController<Fornecedor> {
    public FornecedorController(FornecedorService fornecedorService) {
        super(fornecedorService);
    }

}