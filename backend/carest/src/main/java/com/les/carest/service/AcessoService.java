package com.les.carest.service;

import com.les.carest.model.Acesso;
import com.les.carest.model.Cliente;
import com.les.carest.repository.AcessoRepository;
import com.les.carest.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class AcessoService extends _GenericService<Acesso, AcessoRepository> {

    private final AcessoRepository acessoRepository;
    private final ClienteRepository clienteRepository;

    public AcessoService(AcessoRepository acessoRepository, ClienteRepository clienteRepository) {
        super(acessoRepository);
        this.acessoRepository = acessoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional(readOnly = true)
    public Cliente findClienteByCodigo(String codigo) {
        return clienteRepository.findByCodigo(codigo);
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

        if (cliente.isEm_uso()) {
            throw new RuntimeException("Cliente já está em uso");
        }

        if (cliente.getBloqueado()) {
            throw new RuntimeException("Cliente bloqueado");
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

        acesso.setSaida(new Date());
        atualizarEstadoUsoCliente(acesso.getCliente().getId(), false);
        return atualizarAcesso(acesso);
    }

}