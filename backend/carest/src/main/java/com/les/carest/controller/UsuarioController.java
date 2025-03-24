package com.les.carest.controller;

import com.les.carest.model.Usuario;
import com.les.carest.service.UsuarioService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping({"/usuario"})
public class UsuarioController extends _GenericController<Usuario> {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        super(usuarioService);
        this.usuarioService = usuarioService;
    }
}
/*<<<<<<< HEAD

    
}
=======
}
>>>>>>> refs/remotes/origin/main
*/