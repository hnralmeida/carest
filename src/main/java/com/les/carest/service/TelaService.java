package com.les.carest.service;

import com.les.carest.model.Tela;
import com.les.carest.repository.TelaRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
@Service
@Tag(name = "TelaService", description = "Operações relacionadas a Telas")
public class TelaService extends _GenericService<Tela, TelaRepository> {

    public TelaService(TelaRepository telaRepository) {
        super(telaRepository);
    }

    /**
     * Busca uma tela por nome exato (case sensitive)
     * @param nome Nome exato da tela
     * @return Tela encontrada
     * @throws RuntimeException Se não encontrar ou encontrar múltiplos resultados
     */
    public Tela buscarPorNomeExato(String nome) {
        return this.repositoryGenerics.findFirstByNome(nome)
                .orElseThrow(() -> new RuntimeException("Tela com nome '" + nome + "' não encontrada"));
    }

    /**
     * Busca telas que contenham parte do nome (case insensitive)
     * @param parteNome Parte do nome a ser pesquisada
     * @return Lista de telas encontradas
     */
    public List<Tela> buscarPorParteDoNome(String parteNome) {
        return this.repositoryGenerics.findByNomeContainingIgnoreCase(parteNome);
    }

    /**
     * Verifica se existe uma tela com o nome especificado
     * @param nome Nome da tela
     * @return true se existir, false caso contrário
     */
    public boolean existePorNome(String nome) {
        return this.repositoryGenerics.existsByNome(nome);
    }

    /**
     * Busca todas as telas ordenadas por nome
     * @return Lista ordenada de telas
     */
    public List<Tela> buscarTodasOrdenadasPorNome() {
        return this.repositoryGenerics.findAllByOrderByNomeAsc();
    }

    /**
     * Cria ou atualiza uma tela garantindo a unicidade do nome
     * @param tela Tela a ser salva
     * @return Tela salva
     * @throws RuntimeException Se já existir uma tela com o mesmo nome (caso de criação)
     */
    public Tela salvarTela(Tela tela) {
        // Verifica se é uma nova tela ou atualização
        if (tela.getId() == null) {
            if (this.repositoryGenerics.existsByNome(tela.getNome())) {
                throw new RuntimeException("Já existe uma tela com o nome '" + tela.getNome() + "'");
            }
        } else {
            // Para atualização, verifica se o nome está sendo alterado para um que já existe
            Optional<Tela> telaExistente = this.repositoryGenerics.findFirstByNome(tela.getNome());
            if (telaExistente.isPresent() && !telaExistente.get().getId().equals(tela.getId())) {
                throw new RuntimeException("Já existe outra tela com o nome '" + tela.getNome() + "'");
            }
        }

        return this.repositoryGenerics.save(tela);
    }
}