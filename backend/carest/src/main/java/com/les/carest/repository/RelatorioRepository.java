package com.les.carest.repository;

import com.les.carest.model.Cliente;
import com.les.carest.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface RelatorioRepository extends JpaRepository<Venda,UUID> {//mudar isso porque fica estranho precisa colocar jdbcTemplate ou algo do genero para fazer as consultas

    @Query("SELECT AVG(v.valorTotal) FROM Venda v WHERE v.cliente.id = :clienteId")
    Double getTicketMedio(@Param("clienteId") UUID clienteId);

    // Ticket Médio para múltiplos clientes - retorna List<Object[]>
    // Onde Object[0] = clienteId, Object[1] = valorMedio
    @Query("SELECT v.cliente.id, AVG(v.valorTotal) " +
            "FROM Venda v " +
            "WHERE v.dataVenda BETWEEN :dataInicio AND :dataFim " +
            "GROUP BY v.cliente.id")
    List<Object[]> getTicketMedioMultiplosClientes(@Param("dataInicio")Date dataInicio,@Param("dataFim") Date dataFim);



    // Última venda para um cliente - retorna Object[]
    // Onde Object[0] = vendaId, Object[1] = clienteId, Object[2] = dataVenda, Object[3] = valorTotal
    @Query("SELECT v.id, v.cliente.id, v.dataVenda, v.valorTotal " +
            "FROM Venda v " +
            "WHERE v.cliente.id = :idCliente " +
            "ORDER BY v.dataVenda DESC LIMIT 1")
    Object[] getUltimaVenda(@Param("idCliente") UUID idCliente);

//    @Query("SELECT DISTINCT ON (v.cliente.id)  FROM Venda v ORDER BY v.cliente.id, v.dataVenda DESC")
//    List<Object[]> getUltimaVendaMultiplosClientes();


    @Query(value = """
    SELECT 
        v.id as venda_id,
        c.id as cliente_id,
        c.nome as cliente_nome,
        CAST(v.data_venda AS timestamp) as data_venda,
        v.valor_total
    FROM venda v JOIN cliente c ON v.cliente_id = c.id WHERE (c.id, v.data_venda) IN (
    SELECT cliente_id, MAX(data_venda) FROM venda
    GROUP BY cliente_id
    )
    """, nativeQuery = true)
    List<Object[]> findUltimaVendaCadaCliente();

    @Query("SELECT c FROM Cliente c WHERE EXTRACT(MONTH FROM c.nascimento) = EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(DAY FROM c.nascimento) = EXTRACT(DAY FROM CURRENT_DATE)")
    List<Cliente> findAniversariantesDoDia();

    @Query("SELECT c FROM Cliente c WHERE EXTRACT(MONTH FROM c.nascimento) = :mes")
    List<Cliente> findAniversarianteMes(@Param("mes") int mes);

    // Relatório de clientes endividados
    @Query("SELECT c FROM Cliente c WHERE c.saldo < 0")
    List<Cliente> findClientesEndividados();

    // Relatório de gastos diários (com DTO no service)
    @Query("SELECT c.nome, SUM(v.valorTotal), FUNCTION('TO_CHAR', v.dataVenda, 'HH24:MI') " +
            "FROM Venda v JOIN v.cliente c " +
            "WHERE CAST(v.dataVenda AS date) = :data " +
            "GROUP BY c.nome, FUNCTION('TO_CHAR', v.dataVenda, 'HH24:MI') " +
            "ORDER BY SUM(v.valorTotal) DESC")
    List<Object[]> findClientesNoDia(@Param("data") Date data);

    @Query("SELECT " +
            "c.nome, " +
            "SUM(v.valorTotal), " +
            "FUNCTION('TO_CHAR', v.dataVenda, 'HH24:MI') " +
            "FROM Venda v " +
            "JOIN v.cliente c " +
            "WHERE CAST(v.dataVenda AS date) = CURRENT_DATE " +
            "GROUP BY c.nome, FUNCTION('TO_CHAR', v.dataVenda, 'HH24:MI') " +
            "ORDER BY SUM(v.valorTotal) DESC")
    List<Object[]> findClientesDoDia();
/*
    @Query("")
    List<Object[]> calcDREmes(@Param("mes")int mes);

    @Query("")
    Object calcDREdiario(@Param("data")Date data);


 */
}

