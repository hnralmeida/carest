package com.les.carest.service;

import com.les.carest.DTO.AniversarianteDTO;
import com.les.carest.DTO.ProdutoDTO_Serial;
import com.les.carest.model.Cliente;
import com.les.carest.model.ProdutoSerial;
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
@Tag(name = "ClienteService", description = "Acesso aos métodos de Cliente")
public class ClienteService extends _GenericService<Cliente, ClienteRepository> {

    public ClienteService(ClienteRepository clienteRepository) {
        super(clienteRepository);
    }

    public List<Cliente> listarAniversariantesPorMes (int mes) {
        return this.repositoryGenerics.findAniversariantesPorMes(mes);
    }

    public Cliente buscarClientePorCodigo(String codigo) {
        return ((ClienteRepository) this.repositoryGenerics).buscarByCodigo(codigo);
    }

}