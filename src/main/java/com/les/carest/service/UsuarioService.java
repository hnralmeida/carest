package com.les.carest.service;

import com.les.carest.model.Usuario;
import com.les.carest.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;

@Validated
@Service
@Tag(name = "UsuarioService", description = "Acesso aos métodos do Usuario")
public class UsuarioService extends _GenericService<Usuario, UsuarioRepository> {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        super(usuarioRepository);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario criar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return super.criar(usuario);
    }

    @Override
    public Usuario atualizar(UUID id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = buscarPorId(id);

        if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isEmpty()) {
            usuarioExistente.setSenha(passwordEncoder.encode(usuarioAtualizado.getSenha()));
        }

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());

        return super.atualizar(id, usuarioExistente);
    }

    public boolean validarSenha(String email, String senha) {
        // Busca o usuário pelo email
        Optional<Usuario> optionalUsuario = repositoryGenerics.findByEmail(email);

        // Lança uma exceção se o usuário não for encontrado
        Usuario usuario = optionalUsuario.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Verifica se a senha fornecida corresponde à senha criptografada no banco de dados
        return passwordEncoder.matches(senha, usuario.getSenha());
    }
}
