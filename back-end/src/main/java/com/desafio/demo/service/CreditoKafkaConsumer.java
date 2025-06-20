package com.desafio.demo.service;

import com.desafio.demo.model.Credito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CreditoKafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(CreditoKafkaConsumer.class);

    @KafkaListener(topics = "credito-consulta-topic", groupId = "credito-group")
    public void consume(Credito credito) {
        logger.info("Mensagem recebida no tópico credito-consulta-topic: {}", credito);
    }
}