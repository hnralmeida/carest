package com.les.carest.controller;

import com.les.carest.model.Permissao;
import com.les.carest.model.Tela;
import com.les.carest.model.Usuario;
import com.les.carest.service.TelaService;
import com.les.carest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Tela>> telasPorUsuario(@PathVariable UUID id) {
        Usuario usuario = usuarioService.buscarPorId(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        // Supondo que usuario.getPermissoes() retorna uma lista de Permissao
        List<Tela> telasPermitidas = usuario.getPermissoes().stream()
                .filter(Permissao::isRead) // ou permissao -> permissao.getRead() == true
                .map(Permissao::getTela)
                .collect(Collectors.toList());

        return ResponseEntity.ok(telasPermitidas);
    }
}