package com.les.carest.service;

import com.les.carest.model.Acesso;
import com.les.carest.model.Cliente;
import com.les.carest.model.Venda;
import com.les.carest.repository.AcessoRepository;
import com.les.carest.repository.ClienteRepository;
import com.les.carest.repository.VendaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
        List<Acesso> acessos = acessoRepository.findUltimoAcessoAtivo(codigoCliente, (Pageable) PageRequest.of(0, 1));
        if (acessos.isEmpty()) {
            throw new RuntimeException("Nenhum acesso ativo encontrado para este cliente");
        }
        return acessos.get(0);
    }

    @Transactional
    public void atualizarEstadoUsoCliente(UUID clienteId, boolean emUso) {
        clienteRepository.updateEstadoUso(clienteId, emUso);
    }

    @Transactional
    public Boolean emDia(String codigo) {
        Cliente cliente = findClienteByCodigo(codigo);

        if(cliente.getSaldo()>0) return true;

        Date divida = cliente.getDividaData();
        Date hoje = new Date();

        // Calcula data limite: hoje - 30 dias
        Calendar cal = Calendar.getInstance();
        cal.setTime(hoje);
        cal.add(Calendar.DAY_OF_YEAR, -30); // Subtrai 30 dias
        Date limite = cal.getTime();

        // Se a data da dívida for anterior à data limite, bloqueia
        if (divida.before(limite)) {
            cliente.setBloqueado(true);
            clienteRepository.save(cliente);
            return false;
        } else {
            return true;
        }
    }

    @Transactional
    public Acesso registrarEntrada(String codigo) {
        Cliente cliente = findClienteByCodigo(codigo);

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
        List<Acesso> acessos = acessoRepository.findUltimoAcessoAtivo(codigoCliente, (Pageable) PageRequest.of(0, 1));
        if (acessos.isEmpty()) {
            throw new RuntimeException("Nenhum acesso ativo encontrado para este cliente");
        }
        Acesso acesso = acessos.get(0);

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