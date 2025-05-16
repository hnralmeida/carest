package com.les.carest.service;

import com.les.carest.model.Cliente;
import com.les.carest.relatoriosDTO.AniversarianteDTO;
import com.les.carest.relatoriosDTO.ClienteDiarioDTO;
import com.les.carest.relatoriosDTO.TicketMedioDTO;
import com.les.carest.relatoriosDTO.UltimaVendaDTO;
import com.les.carest.repository.RelatorioRepository;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public RelatorioService(RelatorioRepository relatorioRepository) {
        this.relatorioRepository = relatorioRepository;
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

    private String formatarData(Date data) {
        return data != null ? dateFormat.format(data) : "N/A";
    }
}