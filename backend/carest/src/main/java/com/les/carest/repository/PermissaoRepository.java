package com.les.carest.repository;

import com.les.carest.model.Permissao;
import com.les.carest.model.Tela;
import com.les.carest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, UUID> {

    Optional<Permissao> findByUsuarioIdAndTelaId(UUID usuarioId, UUID telaId);

    List<Permissao> findByUsuarioId(UUID usuarioId);

    List<Permissao> findByTelaId(UUID telaId);

    Optional<Permissao> findByUsuarioAndTela(Usuario usuario, Tela tela);

    boolean existsByUsuarioIdAndTelaId(UUID usuarioId, UUID telaId);

    // Consulta simplificada para verificar permissões
    @Query("SELECT p FROM Permissao p WHERE " +
            "p.usuario.id = :usuarioId AND " +
            "p.tela.id = :telaId AND " +
            "(:acao = 'CREATE' AND p.create = true OR " +
            ":acao = 'READ' AND p.read = true OR " +
            ":acao = 'UPDATE' AND p.update = true OR " +
            ":acao = 'DELETE' AND p.delete = true)")
    boolean verificaPermissao(@Param("usuarioId") UUID usuarioId,
                              @Param("telaId") UUID telaId,
                              @Param("acao") String acao);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM Permissao p WHERE " +
            "p.usuario.id = :usuarioId AND " +
            "p.tela.id = :telaId AND " +
            "(:acao = 'CREATE' AND p.create = true OR " +
            ":acao = 'READ' AND p.read = true OR " +
            ":acao = 'UPDATE' AND p.update = true OR " +
            ":acao = 'DELETE' AND p.delete = true)")
    boolean existsByUsuarioIdAndTelaIdAndAcao(@Param("usuarioId") UUID usuarioId,
                                              @Param("telaId") UUID telaId,
                                              @Param("acao") String acao);

    boolean existsByUsuarioIdAndTelaIdAndCreate(UUID usuarioId, UUID telaId, boolean create);

    void deleteByUsuarioIdAndTelaId(UUID usuarioId, UUID telaId);
}