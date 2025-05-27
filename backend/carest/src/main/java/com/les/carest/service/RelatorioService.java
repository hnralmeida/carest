package com.les.carest.service;

import com.les.carest.DTO.relatorios.*;
import com.les.carest.model.Cliente;
import com.les.carest.model.Saidas;
import com.les.carest.model.Venda;
import com.les.carest.repository.RelatorioRepository;
import com.les.carest.repository.SaidasRepository;
import com.les.carest.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;
    private final SaidasRepository saidasRepository;
    private VendaRepository vendaRepository;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public RelatorioService(RelatorioRepository relatorioRepository,
                            SaidasRepository saidasRepository,
                            VendaRepository vendaRepository)
    {
        this.saidasRepository = saidasRepository;
        this.relatorioRepository = relatorioRepository;
        this.vendaRepository = vendaRepository;
    }

    public List<TicketMedioDTO> getTicketMedioMultiplosClientes(Date dataInicio, Date dataFim) {
        return relatorioRepository.getTicketMedioMultiplosClientes(dataInicio, dataFim)
                .stream()
                .map(result -> {
                    UUID clienteId = (UUID) result[0];
                    Double ticketMedio = ((Number) result[1]).doubleValue();
                    Cliente cliente = relatorioRepository.findClienteById(clienteId);
                    return new TicketMedioDTO(cliente, ticketMedio);
                })
                .collect(Collectors.toList());
    }

    public List<TicketMedioDTO> getTicketMedioPeriodo(Date dataInicio, Date dataFim) {
        return relatorioRepository.getTicketMedioMultiplosClientes(
                        parseDate(dataInicio),
                        parseDate(dataFim)
                ).stream()
                .map(result -> new TicketMedioDTO(
                        (Cliente) result[0],
                        (Double) result[1]
                ))
                .collect(Collectors.toList());
    }

    public UltimaVendaDTO getUltimaVenda(UUID clienteId) {
        Object[] result = relatorioRepository.getUltimaVenda(clienteId);
        return result != null ? new UltimaVendaDTO(result) : null;
    }
    public List<UltimaVendaDTO> getUltimasVendasTodosClientes() {
        return relatorioRepository.findUltimaVendaTodosClientes().stream()
                .map(this::mapToUltimaVendaDTO)
                .collect(Collectors.toList());
    }

    // ---- RELATÓRIOS DE CLIENTES ---- //
    public List<AniversarianteDTO> listarAniversariantesPorMes(int mes) {
        return relatorioRepository.findAniversariantesDoMes(mes)
                .stream()
                .map(AniversarianteDTO::new)
                .collect(Collectors.toList());
    }

    public List<AniversarianteDTO> getAniversariantesDoMes(int mes) {
        return relatorioRepository.findAniversarianteMes(mes)
                .stream()
                .map(AniversarianteDTO::new)
                .collect(Collectors.toList());
    }

    public List<ClienteDiarioDTO> getVendasDoDia() {
        return relatorioRepository.findVendasPorDia(new Date())
                .stream()
                .map(this::mapToClienteDiarioDTO)
                .collect(Collectors.toList());
    }

    public List<ClienteDiarioDTO> getVendasPorData(Date data) {
        return relatorioRepository.findVendasPorDia(parseDate(data))
                .stream()
                .map(this::mapToClienteDiarioDTO)
                .collect(Collectors.toList());
    }

    public List<Cliente> getClientesEndividados() {
        return relatorioRepository.findClientesEndividados();
    }


    public List<ProdutoRelatorioDTO> getRelatorioProdutos(Date dataInicio, Date dataFim) {
        List<Object[]> resultados = relatorioRepository.findProdutosSerialVendidosPorPeriodo(dataInicio, dataFim);

        return resultados.stream()
                .map(r -> new ProdutoRelatorioDTO(
                        (String) r[0],  // codigo
                        (String) r[1],  // nome
                        (Double) r[2],  // valor
                        ((Number) r[3]).intValue()  // quantidade
                ))
                .collect(Collectors.toList());
    }

    public List<Double> getConsumoDiarioParaGrafico(Date dataInicio, Date dataFim) {
        // Validação básica
        if (dataInicio == null || dataFim == null) {
            throw new IllegalArgumentException("Datas não podem ser nulas");
        }
        if (dataFim.before(dataInicio)) {
            throw new IllegalArgumentException("Data final deve ser após data inicial");
        }

        return relatorioRepository.findConsumoDiario(dataInicio, dataFim);
    }



    // ---- MÉTODOS AUXILIARES ---- //
    private Date parseDate(Date date) {
        try {
            return date != null ? dateFormat.parse(dateFormat.format(date)) : null;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao formatar data", e);
        }
    }

    private ClienteDiarioDTO mapToClienteDiarioDTO(Object[] result) {
        return new ClienteDiarioDTO(
                (String) result[0],  // nome
                (Double) result[1],  // valorTotal
                formatarData((Date) result[2])  // data formatada
        );
    }

    private UltimaVendaDTO mapToUltimaVendaDTO(Object[] resultado) {
        Timestamp timestamp = (Timestamp) resultado[3];
        LocalDateTime dataVenda = timestamp.toLocalDateTime();

        return new UltimaVendaDTO(
                (UUID) resultado[0],     // vendaId
                (UUID) resultado[1],     // clienteId
                (String) resultado[2],   // clienteNome
                dataVenda,  // dataVenda
                ((Number) resultado[4]).doubleValue()          // valor
        );
    }


    private String formatarData(Date data) {
        return data != null ? dateFormat.format(data) : "N/A";
    }

    public List<DesempenhoDTO> gerarRelatorioDRE(Date dataInicio, Date dataFim) {
        ZoneId zoneId = ZoneId.systemDefault();

        // Função utilitária para zerar a hora do Date (para agrupar por dia)
        Function<Date, Date> normalizarData = date ->
                Date.from(date.toInstant().atZone(zoneId).toLocalDate()
                        .atStartOfDay(zoneId).toInstant());

        Map<Date, Double> saidasPorDia = saidasRepository
                .findByDataPagamentoBetween(dataInicio, dataFim)
                .stream()
                .collect(Collectors.groupingBy(
                        s -> normalizarData.apply(s.getDataPagamento()),
                        Collectors.summingDouble(Saidas::getValor)
                ));

        Map<Date, List<Venda>> vendasPorDia = vendaRepository
                .findByDataVendaBetween(dataInicio, dataFim)
                .stream()
                .collect(Collectors.groupingBy(
                        v -> normalizarData.apply(v.getDataVenda())
                ));

        Set<Date> dias = new HashSet<>();
        dias.addAll(saidasPorDia.keySet());
        dias.addAll(vendasPorDia.keySet());

        return dias.stream()
                .sorted()
                .map(data -> {
                    double saidas = saidasPorDia.getOrDefault(data, 0.0);
                    List<Venda> vendas = vendasPorDia.getOrDefault(data, List.of());
                    double entradas = vendas.stream().mapToDouble(Venda::getValorTotal).sum();
                    long clientes = vendas.stream().map(v -> v.getCliente().getId()).distinct().count();
                    return new DesempenhoDTO(data, entradas, saidas, clientes);
                })
                .toList();
    }

}