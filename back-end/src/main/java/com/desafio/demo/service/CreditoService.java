package com.desafio.demo.service;

import com.desafio.demo.model.Credito;
import com.desafio.demo.repository.CreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditoService {

    @Autowired
    private CreditoRepository repository;

    @Autowired
    private KafkaTemplate<String, Credito> kafkaTemplate;

    public List<Credito> findByNumeroNfse(String numeroNfse) {
        List<Credito> creditos = repository.findByNumeroNfse(numeroNfse);
        creditos.forEach(credito -> kafkaTemplate.send("credito-consulta-topic", credito.getNumeroNfse(), credito));
        return creditos;
    }

    public Credito findByNumeroCredito(String numeroCredito) {
        Credito credito = repository.findByNumeroCredito(numeroCredito)
                .orElseThrow(() -> new RuntimeException("Crédito não encontrado"));
        kafkaTemplate.send("credito-consulta-topic", credito.getNumeroCredito(), credito);
        return credito;
    }
}