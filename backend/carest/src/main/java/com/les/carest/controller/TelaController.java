package com.les.carest.controller;

import com.les.carest.model.Tela;
import com.les.carest.service.TelaService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/tela")
public class TelaController extends _GenericController<Tela> {
    public TelaController(TelaService telaService) {
        super(telaService);
    }

}