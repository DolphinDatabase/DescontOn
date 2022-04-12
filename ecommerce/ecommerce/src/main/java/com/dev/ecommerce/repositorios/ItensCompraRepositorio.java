package com.dev.ecommerce.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.ecommerce.modelos.ItensCompra;

public interface ItensCompraRepositorio extends JpaRepository<ItensCompra, Long> {
}