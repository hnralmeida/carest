package com.les.carest.service;

import com.les.carest.model.Cliente;
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
                + "----------------------------\n\n\n\n\n"
                + "----------------------------\n\n\n\n\n"
                + "----------------------------\n\n\n\n\n";

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
}
