package com.desafio.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.demo.model.Credito;

import java.util.List;
import java.util.Optional;

public interface CreditoRepository extends JpaRepository<Credito, Long> {
    List<Credito> findByNumeroNfse(String numeroNfse);
    Optional<Credito> findByNumeroCredito(String numeroCredito);
}