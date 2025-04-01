//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.les.carest.service;

import com.les.carest.model.Permissao;
import com.les.carest.model.Tela;
import com.les.carest.model.Usuario;
import com.les.carest.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@Tag(
    name = "UsuarioService",
    description = "Acesso aos métodos do Usuario"
)
public class UsuarioService extends _GenericService<Usuario, UsuarioRepository> {
    private final PasswordEncoder passwordEncoder;
    private final PermissaoService permissaoService;
    private final TelaService telaService;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
                          PermissaoService permissaoService, TelaService telaService) {
        super(usuarioRepository);
        this.passwordEncoder = passwordEncoder;
        this.permissaoService = permissaoService;
        this.telaService = telaService;
    }

    public Usuario criar(Usuario usuario) {
        usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
        return (Usuario)super.criar(usuario);
    }

    public Usuario atualizar(UUID id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = (Usuario)this.buscarPorId(id);
        if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isEmpty()) {
            usuarioExistente.setSenha(this.passwordEncoder.encode(usuarioAtualizado.getSenha()));
        }

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        return (Usuario)super.atualizar(id, usuarioExistente);
    }

    public boolean validarSenha(String email, String senha) {
        Optional<Usuario> optionalUsuario = Optional.ofNullable(((UsuarioRepository) this.repositoryGenerics).findByEmail(email));
        Usuario usuario = (Usuario)optionalUsuario.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return this.passwordEncoder.matches(senha, usuario.getSenha());
    }

    public List<Permissao> buscarPermissoesPorUsuarioId(UUID usuarioId) {
        Usuario usuario = this.buscarPorId(usuarioId);
        return usuario.getPermissoes();
    }

    public void fazerPermissoes(UUID usuarioId) {
        Usuario usuario = this.buscarPorId(usuarioId);
        List<Tela> telas = telaService.listar();

        for(Tela tela : telas) {
            Permissao permissao = new Permissao(usuario, tela, false, false, false, false);
            permissaoService.criar(permissao);
        }

    }
}
