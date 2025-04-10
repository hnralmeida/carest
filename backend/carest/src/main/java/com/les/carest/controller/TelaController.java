package com.les.carest.controller;

import com.les.carest.model.Tela;
import com.les.carest.model.Usuario;
import com.les.carest.service.TelaService;
import com.les.carest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/tela")
public class TelaController extends _GenericController<Tela> {

    @Autowired
    UsuarioService usuarioService;

    public TelaController(TelaService telaService, UsuarioService usuarioService) {
        super(telaService);
        this.usuarioService = usuarioService;
    }

    @GetMapping("/autoGenerate")
    public void AutoGerarTelas(){
        List<Usuario> l = usuarioService.listar();
        for (Usuario user : l ) {
            usuarioService.fazerPermissoes(user.getId());
        }
    }
}