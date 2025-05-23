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
public interface RelatorioRepository extends JpaRepository<Venda, UUID> {

    // ANIVERSARIANTES DO DIA (PostgreSQL compatible)
    @Query("SELECT c FROM Cliente c " +
            "WHERE EXTRACT(DAY FROM c.nascimento) = EXTRACT(DAY FROM CURRENT_DATE) " +
            "AND EXTRACT(MONTH FROM c.nascimento) = EXTRACT(MONTH FROM CURRENT_DATE)")
    List<Cliente> findAniversariantesDoDia();

    // ANIVERSARIANTES DO MÊS (PostgreSQL compatible)
    @Query("SELECT c FROM Cliente c " +
            "WHERE EXTRACT(MONTH FROM c.nascimento) = :mes")
    List<Cliente> findAniversarianteMes(@Param("mes") int mes);

    // TICKET MÉDIO POR PERÍODO (PostgreSQL compatible)
    @Query("SELECT v.cliente.id, AVG(v.valorTotal) " +
            "FROM Venda v " +
            "WHERE CAST(v.dataVenda AS date) BETWEEN CAST(:dataInicio AS date) AND CAST(:dataFim AS date) " +
            "GROUP BY v.cliente.id")
    List<Object[]> getTicketMedioMultiplosClientes(
            @Param("dataInicio") Date dataInicio,
            @Param("dataFim") Date dataFim);

    // VENDAS POR DIA (PostgreSQL compatible)
    @Query("SELECT c.nome, SUM(v.valorTotal), CAST(v.dataVenda AS date) " +
            "FROM Venda v JOIN v.cliente c " +
            "WHERE CAST(v.dataVenda AS date) = CAST(:data AS date) " +
            "GROUP BY c.nome, CAST(v.dataVenda AS date)")
    List<Object[]> findVendasPorDia(@Param("data") Date data);

    // ÚLTIMA VENDA POR CLIENTE (PostgreSQL compatible)
    @Query(value = "SELECT v.id, v.cliente_id, v.data_venda, v.valor_total " +
            "FROM venda v " +
            "WHERE v.cliente_id = :idCliente " +
            "ORDER BY v.data_venda DESC LIMIT 1",
            nativeQuery = true)
    Object[] getUltimaVenda(@Param("idCliente") UUID idCliente);

    // CLIENTES ENDIVIDADOS (mantido)
    @Query("SELECT c FROM Cliente c WHERE c.saldo < 0")
    List<Cliente> findClientesEndividados();

    // Consulta nativa otimizada para última venda por cliente
    @Query(value = """
        SELECT v.id as venda_id, v.cliente_id, c.nome as cliente_nome, 
               v.data_venda, v.valor_total
        FROM venda v
        JOIN cliente c ON v.cliente_id = c.id
        WHERE (v.cliente_id, v.data_venda) IN (
            SELECT cliente_id, MAX(data_venda) 
            FROM venda
            GROUP BY cliente_id
        )
        ORDER BY c.nome
        """, nativeQuery = true)
    List<Object[]> findUltimaVendaTodosClientes();



    @Query("""
    SELECT p.codigo, p.nome, p.valor, SUM(iv.quantidade)
    FROM ItemVenda iv
    JOIN iv.produto p
    JOIN iv.venda v
    WHERE TYPE(p) = ProdutoSerial
    AND CAST(v.dataVenda AS date) BETWEEN CAST(:dataInicio AS date) AND CAST(:dataFim AS date)
    GROUP BY p.codigo, p.nome, p.valor
    """)
    List<Object[]> findProdutosSerialVendidosPorPeriodo(
            @Param("dataInicio") Date dataInicio,
            @Param("dataFim") Date dataFim);


    @Query(nativeQuery = true, value = """
    WITH dias AS (
        SELECT generate_series(
            CAST(:dataInicio AS date),
            CAST(:dataFim AS date),
            '1 day'::interval
        )::date as dia
    )
    SELECT COALESCE(SUM(v.valor_total), 0.0)
    FROM dias d
    LEFT JOIN venda v ON CAST(v.data_venda AS date) = d.dia
    GROUP BY d.dia
    ORDER BY d.dia
    """)
    List<Double> findConsumoDiario(
            @Param("dataInicio") Date dataInicio,
            @Param("dataFim") Date dataFim);

    @Query("SELECT c FROM Cliente c WHERE EXTRACT(MONTH FROM c.nascimento) = :mes")
    List<Cliente> findAniversariantesDoMes(@Param("mes") int mes);

    @Query("SELECT c FROM Cliente c WHERE c.id = :id")
    Cliente findClienteById(@Param("id") UUID id);

}