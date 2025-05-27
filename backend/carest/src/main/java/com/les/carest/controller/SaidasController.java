package com.les.carest.controller;

import com.les.carest.model.Saidas;
import com.les.carest.service.SaidasService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/saidas")
@Tag(name = "Saidas", description = "Gestão de pagamentos realizado a fornecedores")
public class SaidasController extends _GenericController<Saidas>{

    private final SaidasService saidaService;

    @Autowired
    public SaidasController(SaidasService saidaService)  {
        super(saidaService);
        this.saidaService = saidaService;
    }

}