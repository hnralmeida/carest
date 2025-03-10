package com.les.carest.controller;

import com.les.carest.model.Usuario;
import com.les.carest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Validated
@RestController
@RequestMapping("/usuario")
public class UsuarioController extends GenericController<Usuario> {
    public UsuarioController(UsuarioService usuarioService) {
        super(usuarioService);
    }

}