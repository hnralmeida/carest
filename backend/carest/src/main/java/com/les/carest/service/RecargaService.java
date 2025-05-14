package com.les.carest.service;

import com.les.carest.DTO.ClienteDTO;
import com.les.carest.DTO.RecargaDTO;
import com.les.carest.model.Cliente;
import com.les.carest.model.Recarga;
import com.les.carest.repository.ClienteRepository;
import com.les.carest.repository.RecargaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecargaService extends _GenericService<Recarga, RecargaRepository> {
    private final RecargaRepository recargaRepository;
    private final ClienteRepository clienteRepository; // Adicionado para persistir saldo

    public RecargaService(RecargaRepository repository, ClienteRepository clienteRepository) {
        super(repository);
        this.recargaRepository = repository;
        this.clienteRepository = clienteRepository;
    }


    //MODIFICAR O DTO PARA RECARGA DTO
    private List<ClienteDTO> toDTO(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private ClienteDTO toDTO(Cliente cliente) {//para saldo

        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getLimite(),
                cliente.getTelefone(),
                cliente.getSaldo(),
                cliente.getDividaData(),
                cliente.getCodigo()
        );
    }






    public ClienteDTO adicionarCredito(UUID codigoCliente, double valorRecarga) {
        // Busca o cliente pelo código - corrigido para usar o repositório genérico
        Cliente cliente = recargaRepository.findClienteById(codigoCliente);
        // Validação do valor
        if (valorRecarga <= 0) {
            throw new IllegalArgumentException("Valor da recarga deve ser positivo");
        }

        // Atualiza o saldo
        double novoSaldo = cliente.getSaldo() + valorRecarga;
        cliente.setSaldo(novoSaldo);

        // Salva a alteração - usando o repositório genérico
        clienteRepository.save(cliente);

        // Converte para DTO usando o método correto (com saldo)
        return toDTO(cliente);
    }




    public ClienteDTO adicionarLimite(UUID codigoCliente, double novoLimite) {
        // Busca o cliente pelo código - corrigido para usar o repositório genérico
        Cliente cliente = recargaRepository.findClienteById(codigoCliente);
        // Validação do valor
        if (novoLimite <= 0) {
            throw new IllegalArgumentException("Valor do limite deve ser positivo");
        }

        // Atualiza o saldo

        cliente.setLimite(novoLimite);

        // Salva a alteração - usando o repositório genérico
        clienteRepository.save(cliente);

        // Converte para DTO usando o método
        return toDTO(cliente);
    }


    //mudar estado do cliente
    public ClienteDTO mudarEstadoDeAcesso(UUID idCliente) {
        Cliente cliente = recargaRepository.findClienteById(idCliente);

        // Valida se o cliente existe
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        cliente.setBloqueado(!cliente.getBloqueado());
        clienteRepository.save(cliente);
        return toDTO(cliente);
    }

    public ClienteDTO mudarEstadoDeUso(UUID idCliente) {
        Cliente cliente = recargaRepository.findClienteById(idCliente);

        // Valida se o cliente existe
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        cliente.setEm_uso(!cliente.isEm_uso());
        clienteRepository.save(cliente);
        return toDTO(cliente);
    }

    // -- Métodos auxiliares -- //
    private RecargaDTO toRecargaDTO(Recarga recarga) {
        return new RecargaDTO(
                recarga.getCliente().getId(),
                recarga.getValor(),
                recarga.getData()
        );
    }

}