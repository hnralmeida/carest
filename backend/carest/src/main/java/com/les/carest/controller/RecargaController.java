package com.les.carest.controller;

import com.les.carest.model.Recarga;
import com.les.carest.service.RecargaService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Validated
@RestController
@RequestMapping("/recarga")
public class RecargaController extends _GenericController<Recarga> {
    public RecargaController(RecargaService recargaService) {
        super(recargaService);
    }

}