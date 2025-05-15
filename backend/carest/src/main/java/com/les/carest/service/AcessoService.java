package com.les.carest.service;


import com.les.carest.model.Acesso;
import com.les.carest.model.Cliente;
import com.les.carest.repository.AcessoRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
@Service
@Tag(name = "AcessoService", description = "Entrada e saida")
public class AcessoService extends _GenericService<Acesso, AcessoRepository> {
    private AcessoRepository acessoRepository;

    public AcessoService(AcessoRepository repository) {
        super(repository);
        this.acessoRepository = repository;
    }

    public Cliente findByCodigo(String codigo) {
        return acessoRepository.findClienteByCodigo(codigo);
    }

    public void criarAcesso(Acesso acesso) {
        acessoRepository.save(acesso);
    }

    public Acesso findUltimoAcesso(String codigo) {
        return acessoRepository.findUltimoAcesso(codigo);
    }

    public void sessao(UUID clienteId, boolean uso) {
        acessoRepository.estadoDeAcesso(clienteId, uso);

    }
}
