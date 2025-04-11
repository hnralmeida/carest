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
                "dashboard", "acesso", "venda", "produto", "cliente",
                "credito", "funcionarios", "em_aberto", "consumo_diario",
                "clientes_diario", "aniversariantes", "ticket", "ultima_compra",
                "relatorio", "fornecedores", "controle_saida", "dre", "tela"
            );

            for (String nome : nomesTelas) {
                if (telaRepository.findByNome(nome).isEmpty()) {
                    Tela novaTela = new Tela();
                    
                    novaTela.setNome(formatarNome(nome));
                    novaTela.setRota("/" + nome); // exemplo, ajuste se necessário
                    telaRepository.save(novaTela);
                }
            }
        };
    }

     public static String formatarNome(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        String[] palavras = input.toLowerCase().replace("_", " ").split(" ");
        StringBuilder resultado = new StringBuilder();

        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                resultado.append(Character.toUpperCase(palavra.charAt(0)));
                if (palavra.length() > 1) {
                    resultado.append(palavra.substring(1));
                }
                resultado.append(" ");
            }
        }

        return resultado.toString().trim();
    }
}
