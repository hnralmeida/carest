package com.les.carest.controller;

import com.les.carest.model.Venda;
import com.les.carest.service.VendaService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/venda")
public class VendaController extends _GenericController<Venda> {
    public VendaController(VendaService vendaService) {
        super(vendaService);
    }

}