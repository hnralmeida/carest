package com.les.carest.controller;

import com.les.carest.DTO.PermissaoDTO;
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

    @PostMapping("/criarPermissoes/{id}")
    public ResponseEntity<String> fazerPermissoes(@PathVariable UUID id) {
        usuarioService.fazerPermissoes(id);

        return ResponseEntity.ok("Permissões criadas");
    }

    @PostMapping("/permitir")
    public ResponseEntity<String> permitir(@RequestBody PermitirDTO permitirDTO) {
        usuarioService.permitir(permitirDTO.getUserId(), permitirDTO.getNomeTela(), permitirDTO.getRotaTela());

        return ResponseEntity.ok("Permissões criadas");
    }

    @PostMapping("/permitir/{id}")
    public ResponseEntity<String> permitirPorId(@PathVariable UUID id, @RequestBody Permissao permissao) {
        usuarioService.permitirTela(permissao, id);

        return ResponseEntity.ok("Permissão atualizada");
    }

    @PostMapping("/atualizarPermissoes/{id}")
    public ResponseEntity<String>  atualizarPermissoes(@PathVariable UUID id, @RequestBody List<Permissao> permissao) {
        usuarioService.atualizarPermissoes(id, permissao);

        return ResponseEntity.ok("Permissão atualizada");
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> usuarioPorEmail(@PathVariable String email) {
        Usuario user = usuarioService.buscarUsuarioPorEmail(email);

        return ResponseEntity.ok(user);
    }
}
