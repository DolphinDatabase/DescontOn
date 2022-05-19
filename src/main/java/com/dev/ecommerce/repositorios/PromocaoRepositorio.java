package com.dev.ecommerce.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.ecommerce.modelos.Promocao;

public interface PromocaoRepositorio extends JpaRepository<Promocao, Long> {

}