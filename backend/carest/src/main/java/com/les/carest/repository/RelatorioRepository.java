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
public interface RelatorioRepository extends JpaRepository<Venda,UUID> {//mudar isso porque fica estranho

    @Query("SELECT AVG(v.valorTotal) FROM Venda v WHERE v.cliente.id = :clienteId")
    Double getTicketMedio(@Param("clienteId") UUID clienteId);

    // Ticket Médio para múltiplos clientes - retorna List<Object[]>
    // Onde Object[0] = clienteId, Object[1] = valorMedio
    @Query("SELECT v.cliente.id, AVG(v.valorTotal) " +
            "FROM Venda v " +
            "WHERE v.cliente.id IN :clientesIds " +
            "GROUP BY v.cliente.id")
    List<Object[]> getTicketMedioMultiplosClientes(@Param("clientesIds") List<UUID> clientesIds);

    // Última venda para um cliente - retorna Object[]
    // Onde Object[0] = vendaId, Object[1] = clienteId, Object[2] = dataVenda, Object[3] = valorTotal
    @Query("SELECT v.id, v.cliente.id, v.dataVenda, v.valorTotal " +
            "FROM Venda v " +
            "WHERE v.cliente.id = :idCliente " +
            "ORDER BY v.dataVenda DESC LIMIT 1")
    Object[] getUltimaVenda(@Param("idCliente") UUID idCliente);

    // Últimas vendas para múltiplos clientes - retorna List<Object[]>
    // Onde cada Object[] contém: [vendaId, clienteId, dataVenda, valorTotal]
    @Query("SELECT v.id, v.cliente.id, v.dataVenda, v.valorTotal " +
            "FROM Venda v " +
            "WHERE v.id IN (SELECT MAX(v2.id) FROM Venda v2 WHERE v2.cliente.id IN :clientesIds GROUP BY v2.cliente.id)")
    List<Object[]> getUltimaVendaMultiplosClientes(@Param("clientesIds") List<UUID> clientesIds);



    ///ANIVERSARIANTE
    @Query("SELECT c FROM Cliente c WHERE EXTRACT(MONTH FROM c.nascimento) = EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(DAY FROM c.nascimento) = EXTRACT(DAY FROM CURRENT_DATE)")
    List<Cliente> findAniversariantesDoDia();

    @Query("SELECT c FROM Cliente c WHERE EXTRACT(MONTH FROM c.nascimento) = :mes AND EXTRACT(DAY FROM c.nascimento) = :dia")
    List<Cliente> findAniversariantesPorData(@Param("mes") int mes, @Param("dia") int dia);


    // Relatório de clientes endividados
    @Query("SELECT c FROM Cliente c WHERE c.saldo < 0")
    List<Cliente> findClientesEndividados();

    // Relatório de vendas por dia (ajustado para retornar Cliente)
    @Query("SELECT DISTINCT v.cliente FROM Venda v WHERE CAST(v.dataVenda AS date) = :dia")
    List<Cliente> findClientesComVendasNoDia(@Param("dia") Date dia);

    // Relatório de gastos diários (com DTO no service)
    @Query("SELECT c.nome, SUM(v.valorTotal), FUNCTION('TO_CHAR', v.dataVenda, 'HH24:MI') " +
            "FROM Venda v JOIN v.cliente c " +
            "WHERE CAST(v.dataVenda AS date) = :data " +
            "GROUP BY c.nome, FUNCTION('TO_CHAR', v.dataVenda, 'HH24:MI') " +
            "ORDER BY SUM(v.valorTotal) DESC")
    List<Object[]> findClientesDiariosPorData(@Param("data") Date data);

}





































