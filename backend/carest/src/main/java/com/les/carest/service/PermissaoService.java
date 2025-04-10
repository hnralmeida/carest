package com.les.carest.service;

import com.les.carest.model.Permissao;
import com.les.carest.model.Tela;
import com.les.carest.model.Usuario;
import com.les.carest.repository.PermissaoRepository;
import com.les.carest.repository.TelaRepository;
import com.les.carest.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PermissaoService extends _GenericService<Permissao, PermissaoRepository> {

    private final TelaRepository telaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PermissaoRepository permissaoRepository;

    public PermissaoService(PermissaoRepository permissaoRepository,
                            TelaRepository telaRepository,
                            UsuarioRepository usuarioRepository) {
        super(permissaoRepository);
        this.permissaoRepository = permissaoRepository;
        this.telaRepository = telaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Permissao atribuirPermissoes(UUID usuarioAlvoId, UUID telaId,
                                        boolean canCreate, boolean canRead,
                                        boolean canUpdate, boolean canDelete) {

//        if (usuarioAtribuidorId.equals(usuarioAlvoId)) {
//            throw new RuntimeException("Um usuário não pode conceder permissões a si mesmo");
//        }

        Usuario usuarioAlvo = usuarioRepository.findById(usuarioAlvoId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Tela tela = telaRepository.findById(telaId)
                .orElseThrow(() -> new RuntimeException("Tela não encontrada"));

//        if (!permissaoRepository.existsByUsuarioIdAndTelaIdAndCreate(usuarioAtribuidorId, telaId, true)) {
//            throw new RuntimeException("Você não tem permissão para conceder permissões nesta tela");
//        }

        Permissao permissao = permissaoRepository.findByUsuarioIdAndTelaId(usuarioAlvoId, telaId)
                .orElse(new Permissao());

        permissao.setUsuario(usuarioAlvo);
        permissao.setTela(tela);
        permissao.setCreate(canCreate);
        permissao.setRead(canRead || canCreate || canUpdate || canDelete);
        permissao.setUpdate(canUpdate);
        permissao.setDelete(canDelete);

        return permissaoRepository.save(permissao);
    }

    public boolean temPermissao(UUID usuarioId, UUID telaId, String acao) {
        return permissaoRepository.existsByUsuarioIdAndTelaIdAndAcao(usuarioId, telaId, acao);
    }

    public List<Permissao> listarPermissoesUsuario(UUID usuarioId) {
        return permissaoRepository.findByUsuarioId(usuarioId);
    }

    @Transactional
    public void removerPermissoes(UUID usuarioId, UUID telaId) {
        permissaoRepository.deleteByUsuarioIdAndTelaId(usuarioId, telaId);
    }

    public Optional<Permissao> buscarPorUsuarioETela(Usuario usuario, Tela tela) {
        return permissaoRepository.findByUsuarioAndTela(usuario, tela);
    }
}