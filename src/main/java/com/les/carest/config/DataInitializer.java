package com.les.carest.config;

import com.les.carest.model.Tela;
import com.les.carest.repository.TelaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initTelas(TelaRepository telaRepository) {
        return args -> {
            List<String> nomesTelas = Arrays.asList(
                    "Acesso", "Vendas", "Produtos", "Clientes",
                    "Crédito", "Funcionários", "Em Aberto", "Consumo diário",
                    "Clientes diário", "Aniversariantes", "Ticket", "Última compra",
                    "Relatório", "Fornecedores", "Controle Saída", "DRE", "Tela"
            );

            List<String> rotasTelas = Arrays.asList(
                    "acesso", "venda", "produto", "cliente",
                    "credito", "funcionarios", "em_aberto", "consumo_diario",
                    "clientes_diario", "aniversariantes", "ticket", "ultima_compra",
                    "relatorio", "fornecedores", "controle_saida", "dre", "tela"
            );

            // Primeiro verifica e remove duplicatas existentes
            removeTelasDuplicadas(telaRepository);

            // Depois cria as telas padrão
            for (int i = 0; i < nomesTelas.size(); i++) {
                String nome = nomesTelas.get(i);
                String rota = rotasTelas.get(i);

                // Usando findFirstByNome em vez de findByNome
                telaRepository.findFirstByNome(nome).ifPresentOrElse(
                        telaExistente -> {
                            // Atualiza a rota se necessário
                            if (!telaExistente.getRota().equals("/" + rota)) {
                                telaExistente.setRota("/" + rota);
                                telaRepository.save(telaExistente);
                            }
                        },
                        () -> {
                            // Cria nova tela se não existir
                            Tela novaTela = new Tela();
                            novaTela.setNome(nome);
                            novaTela.setRota("/" + rota);
                            telaRepository.save(novaTela);
                        }
                );
            }
        };
    }

    private void removeTelasDuplicadas(TelaRepository telaRepository) {
        // Remove telas duplicadas, mantendo apenas a primeira ocorrência de cada nome
        telaRepository.findAll().stream()
                .collect(Collectors.groupingBy(Tela::getNome))
                .values()
                .stream()
                .filter(telas -> telas.size() > 1)
                .forEach(telas -> {
                    // Mantém a primeira e remove as demais
                    Tela telaParaManter = telas.get(0);
                    telas.stream()
                            .skip(1)
                            .forEach(telaRepository::delete);
                });
    }
}