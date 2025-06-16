package com.les.carest.service;

import com.les.carest.model.Cliente;
import com.les.carest.model.ItemVenda;
import com.les.carest.model.Venda;
import org.springframework.stereotype.Service;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class PrinterService {

    public void listarImpressoras() {
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println("Impressoras encontradas no sistema:");
        for (PrintService service : services) {
            System.out.println(" - " + service.getName());
        }
    }

    private static final String NOME_IMPRESSORA = "EPSON TM-T20X Receipt"; // Nome exato da impressora no Windows

    public void imprimirComprovanteSaldo(Cliente cliente) {
        String texto = "\n*** COMPROVANTE DE SALDO ***\n"
                + "Cliente: " + cliente.getNome() + "\n"
                + "Codigo: " + cliente.getCodigo() + "\n"
                + "Saldo: R$ " + String.format("%.2f", cliente.getSaldo()) + "\n"
                + "----------------------------\n"
                + "\n\n\n\n\n\n\n\n\n\n"; // espaço extra para puxar o papel

        InputStream stream = new ByteArrayInputStream(texto.getBytes(StandardCharsets.UTF_8));
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();

        // Procurar a impressora pelo nome
        PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, null);
        PrintService impressoraSelecionada = null;

        for (PrintService service : services) {
            if (service.getName().equalsIgnoreCase(NOME_IMPRESSORA)) {
                impressoraSelecionada = service;
                break;
            }
        }

        if (impressoraSelecionada != null) {
            DocPrintJob job = impressoraSelecionada.createPrintJob();
            Doc doc = new SimpleDoc(stream, flavor, null);

            try {
                job.print(doc, attrs);
                System.out.println("Comprovante enviado para a impressora: " + impressoraSelecionada.getName());
            } catch (PrintException e) {
                System.err.println("Erro ao imprimir: " + e.getMessage());
            }
        } else {
            System.err.println("Impressora '" + NOME_IMPRESSORA + "' não encontrada.");
        }
    }

    public void imprimirSaida(Venda venda) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n*** COMPROVANTE DE COMPRA ***\n");
        sb.append("Cliente: ").append(venda.getCliente().getNome()).append("\n");
        sb.append("Código: ").append(venda.getCliente().getCodigo()).append("\n");
        sb.append("Data: ").append(venda.getDataVenda()).append("\n");
        sb.append("----------------------------\n");

        for (ItemVenda item : venda.getItens()) {
            String nomeProduto = item.getProduto().getNome();
            double precoUnit = item.getPrecoUnitario();
            int qtd = item.getQuantidade();
            double total = precoUnit * qtd;

            sb.append(nomeProduto)
                    .append("  x").append(qtd)
                    .append("  R$").append(String.format("%.2f", total))
                    .append("\n");
        }

        sb.append("----------------------------\n");
        sb.append("Total: R$ ").append(String.format("%.2f", venda.getValorTotal())).append("\n");
        sb.append("\n\n\n");
        sb.append("----------------------------\n");
        sb.append("\n\n\n");

        InputStream stream = new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8));
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();

        PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, null);
        PrintService impressoraSelecionada = null;

        for (PrintService service : services) {
            if (service.getName().equalsIgnoreCase(NOME_IMPRESSORA)) {
                impressoraSelecionada = service;
                break;
            }
        }

        if (impressoraSelecionada != null) {
            DocPrintJob job = impressoraSelecionada.createPrintJob();
            Doc doc = new SimpleDoc(stream, flavor, null);
            try {
                job.print(doc, attrs);
                System.out.println("Venda enviada para a impressora: " + impressoraSelecionada.getName());
            } catch (PrintException e) {
                System.err.println("Erro ao imprimir: " + e.getMessage());
            }
        } else {
            System.err.println("Impressora '" + NOME_IMPRESSORA + "' não encontrada.");
        }
    }

}
