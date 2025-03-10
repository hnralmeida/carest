package com.les.carest.controller;

import com.les.carest.model.Tela;
import com.les.carest.service.TelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/tela")
public class TelaController extends GenericController<Tela> {
    public TelaController(TelaService telaService) {
        super(telaService);
    }

}