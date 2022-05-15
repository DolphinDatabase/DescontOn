package com.dev.ecommerce.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dev.ecommerce.dto.ProdutoDTO;
import com.dev.ecommerce.modelos.ItensPromocao;
import com.dev.ecommerce.modelos.Produto;
import com.dev.ecommerce.modelos.Promocao;
import com.dev.ecommerce.repositorios.ItensPromocaoRepositorio;
import com.dev.ecommerce.repositorios.PromocaoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itensPromocao")
public class itensPromocaoControle {

    @Autowired
    ItensPromocaoRepositorio itensPromocaoRepositorio;

    @Autowired
    PromocaoRepositorio promocaoRepositorio;

    @PostMapping("{id}")
    public List<ItensPromocao> criar(@RequestBody List<Produto> itens,@PathVariable Long id){
        List<ItensPromocao> res = new ArrayList<>();
        Optional<Promocao> promo = promocaoRepositorio.findById(id);
        itensPromocaoRepositorio.deleteByPromocao(id);
        itens.forEach(item->{
            ItensPromocao insert = new ItensPromocao();
            insert.setPromocao(promo.get());
            insert.setProduto(item);
            res.add(itensPromocaoRepositorio.save(insert));
        });
        return res;
    }

    @DeleteMapping("{id}")
    public void deleteByPromocao(@PathVariable Long id){
        itensPromocaoRepositorio.deleteByPromocao(id);
    }
    
    @GetMapping("/promocao/{id}")
    public List<ProdutoDTO> findProduto(@PathVariable Long id){
        List<ItensPromocao> lista =  itensPromocaoRepositorio.findByPromocao(id);
        List<ProdutoDTO> res = new ArrayList<>();
        lista.forEach(item->{
        	Produto insert = item.getProduto();
            res.add(new ProdutoDTO(insert.getId(),insert.getNome(),insert.getValor(),insert.getCategoria(),insert.getStatus()));
        });
        return res;
    }

}
