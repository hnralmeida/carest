package com.les.carest.controller;

import com.les.carest.DTO.PermissaoDTO;
import com.les.carest.DTO.PermitirDTO;
import com.les.carest.DTO.PermitirDTO;
import com.les.carest.DTO.UsuarioDTO;
import com.les.carest.model.Permissao;
import com.les.carest.model.Usuario;
import com.les.carest.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping({"/usuario"})
public class UsuarioController extends _GenericController<Usuario> {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        super(usuarioService);
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}/permissoes")
    public ResponseEntity<List<Permissao>> listarPermissoes(@PathVariable UUID id) {
        List<Permissao> permissoes = usuarioService.buscarPermissoesPorUsuarioId(id);

        return ResponseEntity.ok(permissoes);
    }

    @PostMapping("/permitir")
    public ResponseEntity<String> permitir(@RequestBody PermitirDTO permitirDTO) {
        usuarioService.permitir(permitirDTO.getUserId(), permitirDTO.getNomeTela(), permitirDTO.getRotaTela());

        return ResponseEntity.ok("Permissões criadas");
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> usuarioPorEmail(@PathVariable String email) {
        Usuario user = usuarioService.buscarUsuarioPorEmail(email);

        return ResponseEntity.ok(user);
    }
    
}
