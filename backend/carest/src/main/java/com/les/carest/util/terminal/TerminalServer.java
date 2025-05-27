package com.les.carest.util.terminal;
import com.les.carest.repository.ClienteRepository;
import com.les.carest.service.PrinterService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class TerminalServer {
    private static final int PORTA = 5000;
    private final ExecutorService threadPool = Executors.newCachedThreadPool();
    private final ClienteRepository clienteRepository;
    private final PrinterService printerService;

    public TerminalServer(ClienteRepository clienteRepository, PrinterService printerService) {
        this.clienteRepository = clienteRepository;
        this.printerService = printerService;
    }

    @PostConstruct
    public void iniciar() {
        printerService.listarImpressoras();

        threadPool.execute(() -> {
            try (ServerSocket server = new ServerSocket(PORTA)) {
                System.out.println("Servidor de terminais iniciado na porta " + PORTA);

                while (!Thread.currentThread().isInterrupted()) {
                    Socket socket = server.accept();
                    System.out.println("Novo terminal conectado: " + socket.getInetAddress());
                    threadPool.execute(new TerminalSession(socket, clienteRepository, printerService));
                }
            } catch (IOException e) {
                System.err.println("Erro no servidor: " + e.getMessage());
            }
        });
    }
}