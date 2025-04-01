package com.les.carest.service;

import com.les.carest.DTO.AniversarianteDTO;
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

    public ClienteService(ClienteRepository clienteRepository) {
        super(clienteRepository);
    }

    // Métodos específicos de aniversariantes
    public List<AniversarianteDTO> listarAniversariantesDoDia() {
        List<Cliente> clientes = this.repositoryGenerics.findAniversariantesDoDia();
        return converterParaDTO(clientes);
    }

    public List<AniversarianteDTO> listarAniversariantesPorData(int mes, int dia) {
        List<Cliente> clientes = this.repositoryGenerics.findAniversariantesPorData(mes, dia);
        return converterParaDTO(clientes);
    }

    private List<AniversarianteDTO> converterParaDTO(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::converterClienteParaDTO)
                .collect(Collectors.toList());
    }

    private AniversarianteDTO converterClienteParaDTO(Cliente cliente) {
        Date nascimento = cliente.getNascimento();
        LocalDate dataNasc = nascimento.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        int idade = Period.between(dataNasc, LocalDate.now()).getYears();

        return new AniversarianteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                idade
        );
    }
}