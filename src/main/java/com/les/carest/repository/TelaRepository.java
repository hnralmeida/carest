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

    // Opção recomendada - retorna o primeiro registro encontrado
    @Query("SELECT t FROM Tela t WHERE t.nome = :nome")
    Optional<Tela> findFirstByNome(@Param("nome") String nome);

    // Alternativa - retorna todos os registros com o nome
    @Query("SELECT t FROM Tela t WHERE t.nome = :nome")
    List<Tela> findAllByNome(@Param("nome") String nome);

    // Mantenha os outros métodos existentes...
    @Query("SELECT t FROM Tela t WHERE LOWER(t.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Tela> findByNomeContainingIgnoreCase(@Param("nome") String nome);

    boolean existsByNome(String nome);

    List<Tela> findAllByOrderByNomeAsc();

    @Query("SELECT DISTINCT t FROM Tela t JOIN Permissao p ON t.id = p.tela.id WHERE p.usuario.id = :usuarioId")
    List<Tela> findTelasComPermissoesDoUsuario(@Param("usuarioId") UUID usuarioId);

    @Query("SELECT t FROM Tela t JOIN Permissao p ON t.id = p.tela.id " +
            "WHERE p.usuario.id = :usuarioId AND " +
            "(:permissao = 'CREATE' AND p.create = true OR " +
            ":permissao = 'READ' AND p.read = true OR " +
            ":permissao = 'UPDATE' AND p.update = true OR " +
            ":permissao = 'DELETE' AND p.delete = true)")
    List<Tela> findTelasPorUsuarioEPermissao(
            @Param("usuarioId") UUID usuarioId,
            @Param("permissao") String permissao);

    @Query("SELECT t FROM Tela t WHERE t.id NOT IN " +
            "(SELECT p.tela.id FROM Permissao p WHERE p.usuario.id = :usuarioId)")
    List<Tela> findTelasSemPermissaoParaUsuario(@Param("usuarioId") UUID usuarioId);
}