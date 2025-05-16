package com.les.carest.service;


import com.les.carest.relatoriosDTO.AniversarianteDTO;
import com.les.carest.model.Cliente;
import com.les.carest.relatoriosDTO.ClienteDiarioDTO;
import com.les.carest.relatoriosDTO.TicketMedioDTO;
import com.les.carest.relatoriosDTO.UltimaVendaDTO;
import com.les.carest.repository.RelatorioRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;

    public RelatorioService(RelatorioRepository relatorioRepository) {
        this.relatorioRepository = relatorioRepository;
    }

    // ---- RELATÓRIOS DE VENDAS ---- //
    public Double getTicketMedio(UUID clienteId) {
        return relatorioRepository.getTicketMedio(clienteId);
    }

    public List<TicketMedioDTO> getTicketMedioMultiplosClientes(Date dataInicio, Date dataFim) {
        return relatorioRepository.getTicketMedioMultiplosClientes(dataInicio, dataFim)
                .stream()
                .map(result -> new TicketMedioDTO((UUID) result[0], (Double) result[1]))
                .collect(Collectors.toList());
    }

    public UltimaVendaDTO getUltimaVenda(UUID clienteId) {
        return new UltimaVendaDTO(relatorioRepository.getUltimaVenda(clienteId));
    }

    public List<Cliente> clientesEndividados() {
        return relatorioRepository.findClientesEndividados();
    }

    // Versão alternativa (nativa)
    public List<UltimaVendaDTO> getUltimasVendasComClientesNativo() {
        return relatorioRepository.findUltimaVendaCadaCliente().stream()
                .map(UltimaVendaDTO::new)
                .collect(Collectors.toList());
    }


    // ---- RELATÓRIOS DE CLIENTES ---- //
    public List<AniversarianteDTO> listarAniversariantesPorMes(int mes) {
        return relatorioRepository.findAniversariantesDoMes(mes)
                .stream()
                .map(com.les.carest.relatoriosDTO.AniversarianteDTO::new)
                .collect(Collectors.toList());
    }

    public List<AniversarianteDTO> getAniversariantesPorData(int mes) {
        return relatorioRepository.findAniversarianteMes(mes)
                .stream()
                .map(com.les.carest.relatoriosDTO.AniversarianteDTO::new)
                .collect(Collectors.toList());
    }


    public List<ClienteDiarioDTO> findClientesDiariosComGasto() {
        List<Object[]> results = relatorioRepository.findClientesDoDia();

        return results.stream()
                .map(arr -> new ClienteDiarioDTO(
                        (String) arr[0],       // nome
                        (Double) arr[1],       // valorTotal
                        (String) arr[2]        // horaVenda
                ))
                .collect(Collectors.toList());
    }



    public List<ClienteDiarioDTO> findClientesDiariosData(Date data) {
        List<Object[]> results = relatorioRepository.findClientesNoDia(data);

        return results.stream()
                .map(arr -> new ClienteDiarioDTO(
                        (String) arr[0],       // nome
                        (Double) arr[1],       // valorTotal
                        (String) arr[2]        // horaVenda
                ))
                .collect(Collectors.toList());
    }






    public void relatorioProtudo(){//relatorio de fornecedor nao de produto
        //Tela de relatório de Relatório de produto, apresenta os valores gasto pelos clientes em um produto selecionado para o fornecedor.
    }


    public void diarioDRE(){
        //Tela de relatório de Demonstração do Resultado do Exercício, mostrando os valores iniciais, de entrada e saída diário e final do caixa do restaurante.
        //entrada de dinheiro no dia e o numero de clientes.
    }

    public void historicoRegargas(){
        //retorna o histico de regarcas do usuario

    }
}