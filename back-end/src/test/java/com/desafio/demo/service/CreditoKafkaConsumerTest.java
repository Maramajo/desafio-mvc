package com.desafio.demo.service;

import com.desafio.demo.model.Credito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreditoKafkaConsumerTest {

    @InjectMocks
    private CreditoKafkaConsumer creditoKafkaConsumer;

    @Test
    void consume_ShouldProcessMessageWithoutException() {
        // Arrange
        Credito credito = new Credito();
        credito.setNumeroCredito("123456");
        credito.setNumeroNfse("7891011");

        // Act
        creditoKafkaConsumer.consume(credito);

        // Assert
        // Como o método apenas loga, verificamos que não há exceções
        // Se houver outras lógicas no consume, adicione mocks e verificações aqui
    }
}