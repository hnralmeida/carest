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

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ResponseStatusException;

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

    public Usuario buscarUsuarioPorEmail(String email) {
        Usuario usuario = this.repositoryGenerics.findByEmail(email);
        return usuario;
    }

    public void fazerPermissoes(UUID usuarioId) {
        Usuario usuario = this.buscarPorId(usuarioId);
        List<Tela> telas = telaService.listar();

        for (Tela tela : telas) {
            // Verifica se já existe a permissão
            Optional<Permissao> permissaoExistente = permissaoService.buscarPorUsuarioETela(usuario, tela);

            if (permissaoExistente.isEmpty()) {
                Permissao permissao = new Permissao(usuario, tela, false, false, false, false);
                permissaoService.criar(permissao);
            }
        }
    }

    public void permitir(UUID usuarioId, String nomeTela, String rotaTela) {
        Usuario usuario;
        try {
            usuario = this.buscarPorId(usuarioId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        Tela tela;
        try {
            tela = telaService.buscarPorNome(nomeTela);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tela não encontrada com o nome fornecido");
        }

        if (tela.getNome() == null || tela.getRota() == null || !tela.getNome().equals(nomeTela)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome da tela ou rota inválida");
        }

        Optional<Permissao> permissaoOptional = permissaoService.buscarPorUsuarioETela(usuario, tela);

        if (permissaoOptional.isPresent()) {
            Permissao permissao = permissaoOptional.get();
            permissao.setRead(true);
            permissaoService.atualizar(permissao.getId(), permissao); // ou salvar direto, se for o mesmo método
        } else {
            Permissao novaPermissao = new Permissao(usuario, tela, false, true, false, false);
            permissaoService.criar(novaPermissao);
        }
    }

    public void permitirTela(Permissao permissao, UUID usuarioId) {
        Usuario usuario;
        try {
            usuario = this.buscarPorId(usuarioId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        try {
            Permissao permissaoExistente = permissaoService.buscarPorId(permissao.getId());

            permissaoExistente.setCreate(permissao.isCreate());
            permissaoExistente.setRead(permissao.isRead());
            permissaoExistente.setUpdate(permissao.isUpdate());
            permissaoExistente.setDelete(permissao.isDelete());

            permissaoService.atualizar(permissaoExistente.getId(), permissaoExistente);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Permissão não encontrado");
        }


    }

    public Usuario atualizarPermissoes(UUID id, List<Permissao> permissoes) {
        Usuario usuarioExistente = this.buscarPorId(id);

        // Atualizar permissões
        if (permissoes != null && !permissoes.isEmpty()) {
            for (Permissao permissaoAtualizada : permissoes) {
                Optional<Permissao> permissaoExistenteOpt = permissaoService
                        .buscarPorUsuarioETela(usuarioExistente, permissaoAtualizada.getTela());

                if (permissaoExistenteOpt.isPresent()) {
                    Permissao permissaoExistente = permissaoExistenteOpt.get();

                    permissaoExistente.setCreate(permissaoAtualizada.isCreate());
                    permissaoExistente.setRead(permissaoAtualizada.isRead());
                    permissaoExistente.setUpdate(permissaoAtualizada.isUpdate());
                    permissaoExistente.setDelete(permissaoAtualizada.isDelete());

                    permissaoService.atualizar(permissaoExistente.getId(), permissaoExistente);
                } else {
                    // Se não existir, cria nova permissão
                    permissaoAtualizada.setUsuario(usuarioExistente);
                    permissaoService.criar(permissaoAtualizada);
                }
            }
        }

        return super.atualizar(id, usuarioExistente);
    }

}
