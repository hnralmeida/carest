package com.les.carest.service;

import com.les.carest.model.Tela;
import com.les.carest.repository.TelaRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@Tag(name = "TelaService", description = "Acesso aos métodos da Tela")
public class TelaService extends _GenericService<Tela, TelaRepository> {

    protected TelaService(TelaRepository TelaRepository) {
        super(TelaRepository);
    }

    public Tela buscarPorNome(String nome){
        return this.repositoryGenerics.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Tela com nome '" + nome + "' não encontrada"));
    }
}
