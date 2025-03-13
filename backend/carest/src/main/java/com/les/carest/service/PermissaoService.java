package com.les.carest.service;

import com.les.carest.model.Permissao;
import com.les.carest.repository.PermissaoRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@Tag(name = "PermissaoService", description = "Acesso aos métodos de Permissao")
public class PermissaoService extends _GenericService<Permissao, PermissaoRepository> {

    protected PermissaoService(PermissaoRepository repositoryGenerics) {
        super(repositoryGenerics);
    }


}
