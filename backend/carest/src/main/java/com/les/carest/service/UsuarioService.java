package com.les.carest.service;

import com.les.carest.model.Usuario;
import com.les.carest.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@Tag(name = "UsuarioService", description = "Acesso aos métodos do Usuario")
public class UsuarioService extends _GenericService<Usuario, UsuarioRepository> {

    protected UsuarioService(UsuarioRepository UsuarioRepository) {
        super(UsuarioRepository);
    }
}
