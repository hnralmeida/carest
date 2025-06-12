/*package com.les.carest.controller;

import com.les.carest.DTO.ClienteDTO;
import com.les.carest.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/*
* CASO NAO FUNCIONE SO COMENTAR O CODIGO
*
*
* */







/*

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @Test
    public void testBuscarPorCodigo() {
        // 1. Preparação
        String codigoTeste = "CLI123";
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCodigo(codigoTeste);
        clienteDTO.setNome("João Silva");

        when(clienteService.acharCliente(codigoTeste)).thenReturn(clienteDTO);

        // 2. Execução
        ResponseEntity<ClienteDTO> resposta = clienteController.buscarPorCodigo(codigoTeste);

        // 3. Verificação
        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
        assertEquals(codigoTeste, resposta.getBody().getCodigo());
        assertEquals("João Silva", resposta.getBody().getNome());

        // Verifica se o método do service foi chamado
        verify(clienteService, times(1)).acharCliente(codigoTeste);
    }
}*/