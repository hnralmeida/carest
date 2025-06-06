package com.les.carest.repository;

import com.les.carest.model.Cliente;
import com.les.carest.model.ProdutoSerial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    @Query("SELECT c FROM Cliente c WHERE EXTRACT(MONTH FROM c.nascimento) = EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(DAY FROM c.nascimento) = EXTRACT(DAY FROM CURRENT_DATE)")
    List<Cliente> findAniversariantesDoDia();

    // Consulta para data específica
    @Query("SELECT c FROM Cliente c WHERE EXTRACT(MONTH FROM c.nascimento) = :mes AND EXTRACT(DAY FROM c.nascimento) = :dia")
    List<Cliente> findAniversariantesPorData(@Param("mes") int mes, @Param("dia") int dia);

    // Consulta para mes
    @Query("SELECT c FROM Cliente c WHERE EXTRACT(MONTH FROM c.nascimento) = :mes")
    List<Cliente> findAniversariantesPorMes(@Param("mes") int mes);

    @Query("SELECT u FROM Cliente u WHERE u.codigo=:codigo")
    Cliente buscarByCodigo(@Param("codigo") String codigo);

    @Query("select c from Cliente c where c.saldo < 0")
    List<Cliente> findEndividados();//relatorio?

    @Query("select c from Cliente c where c.codigo =:codigo")
    Cliente findByCodigoCliente(@Param("codigo") String codigo);

    public interface ClienteDiarioProjection {
        String getNome();

        Double getValorTotal();

        String getHoraVenda();
    }


    @Query("SELECT " +
            "c.nome, " +
            "SUM(v.valorTotal), " +
            "FUNCTION('TO_CHAR', v.dataVenda, 'HH24:MI') " +
            "FROM Venda v " +
            "JOIN v.cliente c " +
            "WHERE CAST(v.dataVenda AS date) = CURRENT_DATE " +
            "GROUP BY c.nome, FUNCTION('TO_CHAR', v.dataVenda, 'HH24:MI') " +
            "ORDER BY SUM(v.valorTotal) DESC")
    List<Object[]> findClientesDiariosComGastoRaw();

    @Modifying
    @Transactional
    @Query("UPDATE Cliente c SET c.em_uso = :uso WHERE c.id = :id")
    void updateEstadoUso(@Param("id") UUID id, @Param("uso") boolean uso);

//    // Consulta para data específica
//    @Query("SELECT NEW com.les.carest.DTO.ClienteDiarioDTO(" +
//            "c.nome, " +
//            "SUM(v.valorTotal), " +
//            "FUNCTION('TO_CHAR', v.dataVenda, 'HH24:MI')) " +
//            "FROM Venda v " +
//            "JOIN v.cliente c " +
//            "WHERE CAST(v.dataVenda AS date) = :data " +
//            "GROUP BY c.nome, FUNCTION('TO_CHAR', v.dataVenda, 'HH24:MI') " +
//            "ORDER BY SUM(v.valorTotal) DESC")
//    List<Tuple> findClientesDiariosPorData(@Param("data") Date data);
}

