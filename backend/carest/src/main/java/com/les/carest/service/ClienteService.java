package com.les.carest.service;

import com.les.carest.DTO.ClienteDTO;
import com.les.carest.model.Cliente;
import com.les.carest.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends _GenericService<Cliente, ClienteRepository> {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        super(clienteRepository);
        this.clienteRepository = clienteRepository;
    }


    // Método principal para buscar clientes
    public ClienteDTO acharCliente(String codigo) {
        Cliente cliente = this.repositoryGenerics.findByCodigoCliente(codigo);
        return toDTO(cliente); // Usa a versão com saldo/dívida
    }

    // Para operações financeiras (saldo/dívida)
    public ClienteDTO toDTO(Cliente cliente) {
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