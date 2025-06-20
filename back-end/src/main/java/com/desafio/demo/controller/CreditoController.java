package com.desafio.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.desafio.demo.model.Credito;
import com.desafio.demo.service.CreditoService;

import java.util.List;

@RestController
@RequestMapping("/api/creditos")
public class CreditoController {
    @Autowired
    private CreditoService service;

    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<Credito>> findByNumeroNfse(@PathVariable String numeroNfse) {
        return ResponseEntity.ok(service.findByNumeroNfse(numeroNfse));
    }

    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<Credito> findByNumeroCredito(@PathVariable String numeroCredito) {
        return ResponseEntity.ok(service.findByNumeroCredito(numeroCredito));
    }
}