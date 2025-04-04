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
import java.util.Objects;
import java.util.Optional;
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
        return converterParaDTO(clientes, LocalDate.now().getMonthValue());
    }

    public List<AniversarianteDTO> listarAniversariantesPorData(int mes, int dia) {
        List<Cliente> clientes = this.repositoryGenerics.findAniversariantesPorData(mes, dia);
        return converterParaDTO(clientes);
    }

    public List<Cliente> listarAniversariantesPorMes (int mes) {
        return this.repositoryGenerics.findAniversariantesPorMes(mes);
    }

    private List<AniversarianteDTO> converterParaDTO(List<Cliente> clientes, int mes) {
        return clientes.stream()
                .map(cliente -> converterClienteParaDTO(cliente, mes))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<AniversarianteDTO> converterClienteParaDTO(Cliente cliente, int mes) {
        try {
            Date nascimento = cliente.getNascimento();
            LocalDate dataNasc = nascimento.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            if (dataNasc.getMonthValue() != mes) {
                return Optional.empty();
            }

            int idade = Period.between(dataNasc, LocalDate.now()).getYears();

            return Optional.of(new AniversarianteDTO(
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getEmail(),
                    cliente.getTelefone(),
                    idade,
                    dataNasc
            ));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private int calculateIdade(LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}