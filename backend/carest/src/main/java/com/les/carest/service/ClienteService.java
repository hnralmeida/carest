package com.les.carest.service;

import com.les.carest.DTO.ClienteDTO;
import com.les.carest.DTO.ClienteDiarioDTO;
import com.les.carest.model.Cliente;
import com.les.carest.repository.ClienteRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
@Tag(name = "ClienteService", description = "Acesso aos métodos de Cliente")
public class ClienteService extends _GenericService<Cliente, ClienteRepository> {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        super(clienteRepository);
        this.clienteRepository = clienteRepository;
    }


    // Métodos específicos de aniversariantes
    public List<ClienteDTO> listarAniversariantesDoDia() {
        List<Cliente> clientes = this.repositoryGenerics.findAniversariantesDoDia();
        return converterParaDTO(clientes);
    }

    public List<ClienteDTO> listarAniversariantesPorData(int mes, int dia) {
        List<Cliente> clientes = this.repositoryGenerics.findAniversariantesPorData(mes, dia);
        return converterParaDTO(clientes);
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

    private ClienteDTO toDTO(Cliente cliente) {//para saldo

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

    public List<ClienteDTO> listarEndividados() {
        List<Cliente> clientes = this.repositoryGenerics.findEndividados();
        return toDTO(clientes);
    }

    public ClienteDTO acharCliente(String codigo) {
        Cliente cliente = this.repositoryGenerics.findByCodigoCliente(codigo);
        return toDTO(cliente);
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


    public ClienteDTO adicionarLimite(String codigoCliente, double novoLimite) {
        // Busca o cliente pelo código - corrigido para usar o repositório genérico
        Cliente cliente = this.repositoryGenerics.findByCodigoCliente(codigoCliente);
        // Validação do valor
        if (novoLimite <= 0) {
            throw new IllegalArgumentException("Valor do limite deve ser positivo");
        }

        // Atualiza o saldo

        cliente.setLimite(novoLimite);

        // Salva a alteração - usando o repositório genérico
        this.repositoryGenerics.save(cliente);

        // Converte para DTO usando o método
        return toDTO(cliente);
    }

    public ClienteDTO mudarEstadoDeAcesso(String codigoCliente) {

        Cliente cliente = this.repositoryGenerics.findByCodigoCliente(codigoCliente);

        cliente.setBloqueado(!cliente.getBloqueado());
        this.repositoryGenerics.save(cliente);

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



    public List<ClienteDiarioDTO> findClientesDiariosData(Date data) {
        List<Object[]> results = clienteRepository.findClientesDiariosPorData(data);

        return results.stream()
                .map(arr -> new ClienteDiarioDTO(
                        (String) arr[0],       // nome
                        (Double) arr[1],       // valorTotal
                        (String) arr[2]        // horaVenda
                ))
                .collect(Collectors.toList());
    }



}