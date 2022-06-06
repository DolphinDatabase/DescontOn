package com.dev.ecommerce.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.dev.ecommerce.modelos.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

    @Query(value = "SELECT * FROM produto WHERE id=:id",nativeQuery = true)
    List<Produto> findAllByProduto(@Param("id") Long id);
    
}