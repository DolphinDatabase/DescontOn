package com.dev.ecommerce.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;


import com.dev.ecommerce.modelos.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {
    
}