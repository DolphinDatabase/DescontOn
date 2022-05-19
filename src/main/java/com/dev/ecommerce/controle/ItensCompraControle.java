package com.dev.ecommerce.controle;

import java.util.List;

import com.dev.ecommerce.modelos.ItensCompra;
import com.dev.ecommerce.repositorios.ItensCompraRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itenscompra")
public class ItensCompraControle {
    
    @Autowired
    ItensCompraRepositorio itensCompraRepositorio;

    @PostMapping
    public List<ItensCompra> salvar(@RequestBody List<ItensCompra> itensCompra){
        itensCompra.forEach(iten->{
            itensCompraRepositorio.save(iten);
        });
        return itensCompra;
    }

}
