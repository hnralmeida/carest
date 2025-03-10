package com.les.carest.controller;

import com.les.carest.exception.GenericOperation;
import com.les.carest.service._GenericServiceTypes;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api")
public abstract class GenericController<TipoEntidade> {

    private final _GenericServiceTypes<TipoEntidade> genericService;

    protected GenericController(_GenericServiceTypes<TipoEntidade> genericService) {
        this.genericService = genericService;
    }

    @GetMapping
    @GenericOperation(description = "Listar todos os registros")
    public ResponseEntity<List<TipoEntidade>> listar() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(genericService.listar());
    }

    @GetMapping("/{id}")
    @GenericOperation(description = "Buscar um registro por ID")
    public ResponseEntity<TipoEntidade> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(genericService.buscarPorId(id));
    }

    @PostMapping
    @GenericOperation(description = "Criar um registro")
    public ResponseEntity<TipoEntidade> criar(@Valid @RequestBody TipoEntidade entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(genericService.criar(entity));
    }

    @PutMapping("/{id}")
    @GenericOperation(description = "Atualizar um registro")
    public ResponseEntity<TipoEntidade> atualizar(@PathVariable @Positive UUID id, @RequestBody TipoEntidade entity) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(genericService.atualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    @GenericOperation(description = "Excluir um registro")
    public ResponseEntity<String> excluir(@PathVariable UUID id) {
        genericService.excluir(id);
        if (genericService.buscarPorId(id) == null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Excluído");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Registro não pôde ser excluído");
        }
    }
}