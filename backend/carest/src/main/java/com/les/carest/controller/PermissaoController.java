package com.les.carest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.les.carest.exception.GenericOperation;
import com.les.carest.model.Permissao;
import com.les.carest.model.Tela;
import com.les.carest.service.PermissaoService;
import com.les.carest.service.UsuarioService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/permissao")
public class PermissaoController extends _GenericController<Permissao> {
    private final PermissaoService permissaoService;

    public PermissaoController(PermissaoService permissaoService) {
        super(permissaoService);
        this.permissaoService = permissaoService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Permissao> atualizarParcial(
            @PathVariable @Positive UUID id,
            @RequestBody Permissao permissao,
            @RequestParam UUID userId
    ) {
        // ..Permissao permissaoExistente = permissaoService.buscarPorId(id);

        Permissao atualizada = permissaoService.atribuirPermissoes(userId,
                permissao.getTela().getId(),
                permissao.isCreate(),
                permissao.isRead(),
                permissao.isUpdate(),
                permissao.isDelete()
                );
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(atualizada);
    }


}