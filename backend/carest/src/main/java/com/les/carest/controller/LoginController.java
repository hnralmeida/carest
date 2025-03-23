package com.les.carest.controller;

import com.les.carest.DTO.LoginDTO;
import com.les.carest.exception.GenericOperation;

import com.les.carest.model.Usuario;
import com.les.carest.repository.UsuarioRepository;
import com.les.carest.service.UsuarioService;
import com.les.carest.service._GenericServiceTypes;

import com.les.carest.service.UsuarioService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class LoginController{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public LoginController(UsuarioService genericService) {
    }

    @PostMapping("/login")
    @GenericOperation(description = "Endpoint para autenticação de usuário")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        // Busca o usuário pelo nome
        Optional<Usuario> usuarioOptional = Optional.ofNullable(usuarioRepository.findByEmail(loginDTO.getEmail()));//.orElse(->new Exception)

        // Verifica se o usuário existe
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado");
        }

        // Verifica se a senha está correta
        if (!usuarioOptional.get().getSenha().equals(loginDTO.getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
        }

        // Retorna uma resposta de sucesso
        return ResponseEntity.ok("Login bem-sucedido");
    }
}
