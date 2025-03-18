package com.les.carest.controller;

import com.les.carest.DTO.LoginDTO;
import com.les.carest.exception.GenericOperation;
import com.les.carest.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/auth"})
public class LoginController {
    @Autowired
    private UsuarioService usuarioService;

    public LoginController() {
    }

    @PostMapping({"/login"})
    @GenericOperation(
        description = "Endpoint para autenticação de usuário"
    )
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO) {
        try {
            boolean senhaValida = this.usuarioService.validarSenha(loginDTO.getEmail(), loginDTO.getSenha());
            return senhaValida ? ResponseEntity.ok("Login bem-sucedido!") : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
