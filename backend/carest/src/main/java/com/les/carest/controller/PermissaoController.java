package com.les.carest.controller;

import com.les.carest.model.Permissao;
import com.les.carest.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Validated
@RestController
@RequestMapping("/permissao")
public class PermissaoController extends GenericController<Permissao> {
    public PermissaoController(PermissaoService permissaoService) {
        super(permissaoService);
    }

}