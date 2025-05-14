package com.les.carest.service;

import com.les.carest.DTO.ClienteDTO;
import com.les.carest.model.Cliente;
import com.les.carest.model.ProdutoSerial;
import com.les.carest.relatoriosDTO.ClienteDiarioDTO;
import com.les.carest.repository.ClienteRepository;
import com.les.carest.repository.ProdutoSerialRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@Service
public class ClienteService extends _GenericService<Cliente, ClienteRepository> {


    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        super(clienteRepository);
        this.clienteRepository = clienteRepository;
    }


    public List<Cliente> listarAniversariantesPorMes (int mes) {
        return this.repositoryGenerics.findAniversariantesPorMes(mes);
    }

    private List<ClienteDTO> converterParaDTO(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::converterClienteParaDTO)
                .collect(Collectors.toList());
    }


    private List<ClienteDTO> toDTO(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private ClienteDTO converterClienteParaDTO(Cliente cliente) {//apenas aniversairante
        Date nascimento = cliente.getNascimento();
        LocalDate dataNasc = nascimento.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        int idade = Period.between(dataNasc, LocalDate.now()).getYears();

        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                idade
        );
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
                cliente.getLimite(),
                cliente.getTelefone(),
                cliente.getSaldo(),
                cliente.getDividaData(),
                cliente.getCodigo()
        );
    }

    public List<ClienteDTO> listarEndividados() {
        List<Cliente> clientes = this.repositoryGenerics.findEndividados();
        return toDTO(clientes);
    }


    //TEMPORARIO

    public ClienteDTO adicionarCredito(String codigoCliente, double valorRecarga) {
        // Busca o cliente pelo código - corrigido para usar o repositório genérico
        Cliente cliente = this.repositoryGenerics.findByCodigoCliente(codigoCliente);
        // Validação do valor
        if (valorRecarga <= 0) {
            throw new IllegalArgumentException("Valor da recarga deve ser positivo");
        }

        // Atualiza o saldo
        double novoSaldo = cliente.getSaldo() + valorRecarga;
        cliente.setSaldo(novoSaldo);

        // Salva a alteração - usando o repositório genérico
        this.repositoryGenerics.save(cliente);

        // Converte para DTO usando o método correto (com saldo)
        return toDTO(cliente);
    }

    // Add this method to convert DTO to Entity
    private Cliente toEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setSaldo(clienteDTO.getSaldo());
        cliente.setCodigo(clienteDTO.getCodigo());
        // Note: You'll need to set other required fields like nascimento, codigoCliente, etc.
        // depending on your model structure
        return cliente;
    }


    public List<ClienteDiarioDTO> findClientesDiariosComGasto() {
        List<Object[]> results = clienteRepository.findClientesDiariosComGastoRaw();

        return results.stream()
                .map(arr -> new ClienteDiarioDTO(
                        (String) arr[0],       // nome
                        (Double) arr[1],       // valorTotal
                        (String) arr[2]        // horaVenda
                ))
                .collect(Collectors.toList());
    }

//    // Versão alternativa com tratamento de data específica
//    public List<ClienteDiarioDTO> listarClientesDiariosPorData(Date data) {
//        return clienteRepository.findClientesDiariosPorData(data);
//
//    }

}