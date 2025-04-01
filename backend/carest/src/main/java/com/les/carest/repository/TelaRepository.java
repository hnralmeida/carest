package com.les.carest.repository;

import com.les.carest.model.Tela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TelaRepository extends JpaRepository<Tela, UUID> {

    // Busca tela por nome exato
    Optional<Tela> findByNome(String nome);

    // Busca telas que contenham parte do nome (case insensitive)
    @Query("SELECT t FROM Tela t WHERE LOWER(t.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Tela> findByNomeContainingIgnoreCase(@Param("nome") String nome);

    // Verifica se existe tela com determinado nome
    boolean existsByNome(String nome);

    // Busca telas ordenadas por nome
    List<Tela> findAllByOrderByNomeAsc();

    // Busca telas com permissões de um usuário específico
    @Query("SELECT DISTINCT t FROM Tela t JOIN Permissao p ON t.id = p.tela.id WHERE p.usuario.id = :usuarioId")
    List<Tela> findTelasComPermissoesDoUsuario(@Param("usuarioId") UUID usuarioId);

    // Busca telas onde o usuário tem determinada permissão
    @Query("SELECT t FROM Tela t JOIN Permissao p ON t.id = p.tela.id " +
            "WHERE p.usuario.id = :usuarioId AND " +
            "(:permissao = 'CREATE' AND p.create = true OR " +
            ":permissao = 'READ' AND p.read = true OR " +
            ":permissao = 'UPDATE' AND p.update = true OR " +
            ":permissao = 'DELETE' AND p.delete = true)")
    List<Tela> findTelasPorUsuarioEPermissao(
            @Param("usuarioId") UUID usuarioId,
            @Param("permissao") String permissao);

    // Busca telas que ainda não tem permissão para um usuário específico
    @Query("SELECT t FROM Tela t WHERE t.id NOT IN " +
            "(SELECT p.tela.id FROM Permissao p WHERE p.usuario.id = :usuarioId)")
    List<Tela> findTelasSemPermissaoParaUsuario(@Param("usuarioId") UUID usuarioId);
}