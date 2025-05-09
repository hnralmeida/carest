package com.les.carest.service;

import com.les.carest.DTO.ClienteDTO;
import com.les.carest.DTO.RecargaDTO;
import com.les.carest.model.Cliente;
import com.les.carest.model.Recarga;
import com.les.carest.repository.ClienteRepository;
import com.les.carest.repository.RecargaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class RecargaService extends _GenericService<Recarga, RecargaRepository> {
    private final RecargaRepository recargaRepository;
    private final ClienteRepository clienteRepository; // Adicionado para persistir saldo

    public RecargaService(RecargaRepository repository, ClienteRepository clienteRepository) {
        super(repository);
        this.recargaRepository = repository;
        this.clienteRepository = clienteRepository;
    }

    // 1. Registrar recarga (já existente)
    public RecargaDTO registrarRecarga(RecargaDTO recargaDTO) {
        if (recargaDTO.getValorRecarga() < 0) {
            throw new IllegalArgumentException("Valor da recarga deve ser positivo");
        }

        Cliente cliente = recargaRepository.findClienteById(recargaDTO.getIdCliente())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Recarga recarga = new Recarga();
        recarga.setCliente(cliente);
        recarga.setValor(recargaDTO.getValorRecarga());
        recarga.setData(new Date());
        recargaRepository.save(recarga);

        cliente.setSaldo(cliente.getSaldo() + recargaDTO.getValorRecarga());
        clienteRepository.save(cliente); // Persiste a atualização do saldo

        return toRecargaDTO(recarga);
    }

    // 2. Ajustar limite do cliente
    public ClienteDTO ajustarLimite(UUID clienteId, double novoLimite) {
        if (novoLimite <= 0) {
            throw new IllegalArgumentException("Limite deve ser positivo");
        }

        Cliente cliente = recargaRepository.findClienteById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        cliente.setLimite(novoLimite);
        clienteRepository.save(cliente);

        return toClienteDTO(cliente);
    }

    // 3. Alterar estado de bloqueio
    public ClienteDTO alternarBloqueio(UUID clienteId) {
        Cliente cliente = recargaRepository.findClienteById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        cliente.setBloqueado(!cliente.isEm_uso());
        clienteRepository.save(cliente);

        return toClienteDTO(cliente);
    }

    // -- Métodos auxiliares -- //
    private RecargaDTO toRecargaDTO(Recarga recarga) {
        return new RecargaDTO(
                recarga.getCliente().getId(),
                recarga.getValor(),
                recarga.getData()
        );
    }

    private ClienteDTO toClienteDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getSaldo(),
                cliente.getDividaData(),
                cliente.getCodigo()
        );
    }
}