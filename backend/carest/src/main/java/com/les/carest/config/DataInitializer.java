package com.les.carest.config;

import com.les.carest.model.Tela;
import com.les.carest.repository.TelaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

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

            for (int i = 0; i < nomesTelas.size(); i++) {
                String nome = nomesTelas.get(i);
                String rota = rotasTelas.get(i);

                if (telaRepository.findByNome(nome).isEmpty()) {
                    Tela novaTela = new Tela();
                    novaTela.setNome(nome);
                    novaTela.setRota("/" + rota);
                    telaRepository.save(novaTela);
                }
            }
        };
    }

}
