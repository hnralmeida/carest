package com.les.carest.service;

import com.les.carest.model.Acesso;
import com.les.carest.model.Cliente;
import com.les.carest.model.Venda;
import com.les.carest.repository.AcessoRepository;
import com.les.carest.repository.ClienteRepository;
import com.les.carest.repository.VendaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.UUID;

@Service
public class AcessoService extends _GenericService<Acesso, AcessoRepository> {

    private final AcessoRepository acessoRepository;
    private final ClienteRepository clienteRepository;
    private final VendaRepository vendaRepository;

    private final PrinterService printerService;

    public AcessoService(AcessoRepository acessoRepository, ClienteRepository clienteRepository, PrinterService printerService, VendaRepository vendaRepository) {
        super(acessoRepository);
        this.acessoRepository = acessoRepository;
        this.clienteRepository = clienteRepository;
        this.printerService = printerService;
        this.vendaRepository = vendaRepository;
    }

    @Transactional(readOnly = true)
    public Cliente findClienteByCodigo(String codigo) {
        return clienteRepository.findByCodigoCliente(codigo);
    }

    @Transactional
    public Acesso registrarAcesso(Acesso acesso) {
        return acessoRepository.save(acesso);
    }

    @Transactional(readOnly = true)
    public Acesso findUltimoAcessoAtivo(String codigoCliente) {
        return acessoRepository.findUltimoAcessoAtivo(codigoCliente);
    }

    @Transactional
    public void atualizarEstadoUsoCliente(UUID clienteId, boolean emUso) {
        clienteRepository.updateEstadoUso(clienteId, emUso);
    }


    @Transactional
    public Acesso registrarEntrada(String codigo) {
        Cliente cliente = findClienteByCodigo(codigo);

        if(cliente==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }
        if (cliente.isEm_uso()) {
            throw new RuntimeException("Cliente já está em uso");
        }

        if (cliente.getBloqueado()) {
            throw new RuntimeException("Cliente bloqueado");
        }

        double valorTotal = cliente.getSaldo() + cliente.getLimite();

        if (valorTotal < 0) {
            throw new RuntimeException("Cliente sem saldo");
        }

        atualizarEstadoUsoCliente(cliente.getId(), true);

        Acesso acesso = new Acesso();
        acesso.setCliente(cliente);
        acesso.setEntrada(new Date());
        return registrarAcesso(acesso);
    }

    @Transactional
    public Acesso atualizarAcesso(Acesso acesso) {
        return acessoRepository.save(acesso); // Ou use o método update da sua classe genérica
    }

    @Transactional
    public Acesso registrarSaida(UUID acessoId) {
        Acesso acesso = buscarPorId(acessoId); // Método herdado da classe genérica
        acesso.setSaida(new Date());
        atualizarEstadoUsoCliente(acesso.getCliente().getId(), false);
        return atualizarAcesso(acesso); // Agora este método existe
    }


    @Transactional
    public Acesso registrarSaidaPorCodigoCliente(String codigoCliente) {
        // Busca o último acesso ativo do cliente
        Acesso acesso = acessoRepository.findUltimoAcessoAtivo(codigoCliente);
        if (acesso == null) {
            throw new RuntimeException("Nenhum acesso ativo encontrado para este cliente");
        }
        Venda venda = vendaRepository.findUltimaVendaPorCliente(codigoCliente);

        printerService.imprimirSaida(venda);
        acesso.setSaida(new Date());
        atualizarEstadoUsoCliente(acesso.getCliente().getId(), false);
        return atualizarAcesso(acesso);
    }

}