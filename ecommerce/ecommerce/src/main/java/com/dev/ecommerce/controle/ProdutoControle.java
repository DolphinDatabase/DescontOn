package com.dev.ecommerce.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ecommerce.dto.ProdutoDTO;
import com.dev.ecommerce.modelos.Produto;
import com.dev.ecommerce.repositorios.ProdutoRepositorio;

@RestController
@RequestMapping("/produto")
public class ProdutoControle {

	@Autowired
	private ProdutoRepositorio produtoRepositorio;

	@PostMapping
	public ProdutoDTO create(@RequestBody Produto produto){
		Produto save = produtoRepositorio.save(produto);
		ProdutoDTO res = new ProdutoDTO(save.getId(),save.getNome(),save.getValor(),save.getCategoria(),save.getStatus());
		return res;
	}

	@GetMapping
    public List<ProdutoDTO> index(@RequestParam(required = false) Long id){
        if(id != null){
            List<ProdutoDTO> res = new ArrayList<>();
            Optional<Produto> product = produtoRepositorio.findById(id);
            if(product.isPresent()){
            	Produto exist = product.get();
                res.add(new ProdutoDTO(exist.getId(),exist.getNome(),exist.getValor(),exist.getCategoria(),exist.getStatus()));
            }
            return res;
        }else{
            List<Produto> find = produtoRepositorio.findAll();
            List<ProdutoDTO> res = new ArrayList<>();
            find.forEach(product->{
                res.add(new ProdutoDTO(product.getId(),product.getNome(),product.getValor(),product.getCategoria(),product.getStatus()));
            });
            return res;
        }
    }

	@PostMapping("/verificar")
	public List<ProdutoDTO> verificar(@RequestBody List<Long> ids){
		List<ProdutoDTO> res = new ArrayList<>();
		ids.forEach(id->{
			Optional<Produto> product = produtoRepositorio.findById(id);
			if(product.isPresent()){
				Produto exist = product.get();
				res.add(new ProdutoDTO(exist.getId(), exist.getNome(),exist.getValor(),exist.getCategoria(),exist.getStatus()));
			}
		});
		return res;
	}

	@PutMapping("/{id}")
	public ProdutoDTO update(@RequestBody Produto produto, @RequestParam Long id){
		Optional<Produto> pro = produtoRepositorio.findById(id);
		if(pro.isEmpty()){
			return new ProdutoDTO(null,null,null,null,null);
		}else{
			Produto save = produtoRepositorio.save(produto);
			return new ProdutoDTO(save.getId(),save.getNome(),save.getValor(),save.getCategoria(),save.getStatus());
		}
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id){
		Optional<Produto> pro = produtoRepositorio.findById(id);
		if(pro.isEmpty()){
			return "Produto não encontrado";
		}else{
			try{
				produtoRepositorio.delete(pro.get());
				return "Produto "+pro.get().getId()+" deletado.";
			}catch(Exception err){
				Produto produtoSave = pro.get();
				produtoSave.setStatus(1);
				produtoRepositorio.save(produtoSave);
				return "Produto "+pro.get().getId()+" arquivado.";
			}
		}
	}
}