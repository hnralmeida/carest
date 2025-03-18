package com.les.carest.controller;

import com.les.carest.model.ProdutoSerial;
import com.les.carest.service.ProdutoSerialService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Validated
@RestController
@RequestMapping("/produtoSerial")
public class ProdutoSerialController extends _GenericController<ProdutoSerial> {
    public ProdutoSerialController(ProdutoSerialService produtoSerialService) {
        super(produtoSerialService);
    }

}