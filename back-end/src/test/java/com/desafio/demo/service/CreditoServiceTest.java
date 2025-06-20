package com.desafio.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import com.desafio.demo.model.Credito;
import com.desafio.demo.repository.CreditoRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreditoServiceTest {
    @Mock
    private CreditoRepository repository;
    
    @Mock
    private KafkaTemplate<String, Credito> kafkaTemplate;

    @InjectMocks
    private CreditoService service;

    @Test
    void findByNumeroNfse_ShouldReturnList() {
        Credito credito = new Credito();
        credito.setNumeroNfse("7891011");
        when(repository.findByNumeroNfse("7891011")).thenReturn(Arrays.asList(credito));

        var result = service.findByNumeroNfse("7891011");
        assertFalse(result.isEmpty());
        assertEquals("7891011", result.get(0).getNumeroNfse());
        verify(kafkaTemplate, times(1)).send(eq("credito-consulta-topic"), anyString(), any(Credito.class));
        verify(repository, times(1)).findByNumeroNfse("7891011");
    }

    @Test 
    void findByNumeroCredito_ShouldReturnCredito() {
        Credito credito = new Credito();
        credito.setNumeroCredito("123456");
        when(repository.findByNumeroCredito("123456")).thenReturn(Optional.of(credito));

        var result = service.findByNumeroCredito("123456");
        assertEquals("123456", result.getNumeroCredito());
        verify(kafkaTemplate, times(1)).send(eq("credito-consulta-topic"), anyString(), any(Credito.class));
        verify(repository, times(1)).findByNumeroCredito("123456");
        System.out.println(credito.toString());

    }

    @Test
    void findByNumeroCredito_ShouldThrowException() {
        when(repository.findByNumeroCredito("999999")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.findByNumeroCredito("999999"));
    }
}