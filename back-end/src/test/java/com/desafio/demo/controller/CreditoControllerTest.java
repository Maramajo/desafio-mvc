package com.desafio.demo.controller;

import com.desafio.demo.model.Credito;
import com.desafio.demo.service.CreditoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CreditoController.class)
public class CreditoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditoService creditoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findByNumeroNfse_ShouldReturnListOfCreditos() throws Exception {
        // Arrange
        Credito credito = new Credito();
        credito.setNumeroCredito("123456");
        credito.setNumeroNfse("7891011");
        List<Credito> creditos = Arrays.asList(credito);
        when(creditoService.findByNumeroNfse("7891011")).thenReturn(creditos);

        // Act & Assert
        mockMvc.perform(get("/api/creditos/7891011")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(creditos)));

        verify(creditoService, times(1)).findByNumeroNfse("7891011");
    }

    @Test
    void findByNumeroNfse_ShouldReturnEmptyList() throws Exception {
        // Arrange
        when(creditoService.findByNumeroNfse("9999999")).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/creditos/9999999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(creditoService, times(1)).findByNumeroNfse("9999999");
    }

    @Test
    void findByNumeroCredito_ShouldReturnCredito() throws Exception {
        // Arrange
        Credito credito = new Credito();
        credito.setNumeroCredito("123456");
        credito.setNumeroNfse("7891011");
        when(creditoService.findByNumeroCredito("123456")).thenReturn(credito);

        // Act & Assert
        mockMvc.perform(get("/api/creditos/credito/123456")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(credito)));

        verify(creditoService, times(1)).findByNumeroCredito("123456");
    }


}