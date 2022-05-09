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

import com.dev.ecommerce.modelos.Produto;
import com.dev.ecommerce.repositorios.ProdutoRepositorio;

@RestController
@RequestMapping("/produto")
public class ProdutoControle {

	@Autowired
	private ProdutoRepositorio produtoRepositorio;

	@PostMapping
	public Produto create(@RequestBody Produto produto){
		produtoRepositorio.save(produto);
		return produto;
	}

	@GetMapping
    public List<Produto> index(@RequestParam(required = false) Long id){
        if(id != null){
            List<Produto> res = new ArrayList<>();
            Optional<Produto> product = produtoRepositorio.findById(id);
            if(product.isPresent()){
                res.add(product.get());
            }
            return res;
        }else{
            return produtoRepositorio.findAll();
        }
    }

	@PostMapping("/verificar")
	public List<Produto> verificar(@RequestBody List<Long> ids){
		List<Produto> res = new ArrayList<>();
		ids.forEach(id->{
			Optional<Produto> pro = produtoRepositorio.findById(id);
			if(pro.isPresent()){
				res.add(pro.get());
			}
		});
		return res;
	}

	@PutMapping("/{id}")
	public Produto update(@RequestBody Produto produto, @RequestParam Long id){
		Produto res = new Produto();
		Optional<Produto> pro = produtoRepositorio.findById(id);
		if(pro.isEmpty()){
			return res;
		}else{
			return produtoRepositorio.save(produto);
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
				return "Produto "+pro.get().getId()+" deletedo";
			}catch(Exception err){
				Produto produtoSave = pro.get();
				produtoSave.setStatus(1);
				produtoRepositorio.save(produtoSave);
				return "Produto "+pro.get().getId()+" arquivado";
			}
		}
	}


}
