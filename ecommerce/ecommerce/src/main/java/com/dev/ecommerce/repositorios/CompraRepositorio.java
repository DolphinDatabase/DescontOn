package com.dev.ecommerce.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.ecommerce.modelos.Compra;

public interface CompraRepositorio extends JpaRepository<Compra, Long> {

}
