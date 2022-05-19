package com.dev.ecommerce.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.dev.ecommerce.modelos.ItensPromocao;

public interface ItensPromocaoRepositorio extends JpaRepository<ItensPromocao, Long>{

    @Query(value = "SELECT * FROM itens_promocao WHERE promocao_id=:id",nativeQuery =  true)
    List<ItensPromocao> findByPromocao(@Param("id") Long id);

    @Query(value = "SELECT * FROM itens_promocao WHERE produto_id=:produtoId AND promocao_id=:promocaoId",nativeQuery =  true)
    List<ItensPromocao> findByProduto(@Param("produtoId") Long produtoId,@Param("promocaoId") Long promocaoId);

    @Query(value = "SELECT * FROM itens_promocao WHERE produto_id=:id",nativeQuery = true)
    List<ItensPromocao> findAllByProduto(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM itens_promocao WHERE promocao_id=:id",nativeQuery = true)
    void deleteByPromocao(@Param("id") Long id);

}