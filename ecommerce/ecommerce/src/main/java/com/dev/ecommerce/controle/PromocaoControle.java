package com.dev.ecommerce.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dev.ecommerce.modelos.Promocao;
import com.dev.ecommerce.repositorios.PromocaoRepositorio;

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

@RestController
@RequestMapping("/promocao")
public class PromocaoControle {

	@Autowired
	PromocaoRepositorio promocaoRepositorio;

	@PostMapping
	public Promocao create(@RequestBody Promocao promocao){
		return promocaoRepositorio.save(promocao);
	}

	@GetMapping
    public List<Promocao> index(@RequestParam(required = false) Long id){
        if(id != null){
            List<Promocao> res = new ArrayList<>();
            Optional<Promocao> promocao = promocaoRepositorio.findById(id);
            if(promocao.isPresent()){
                res.add(promocao.get());
            }
            return res;
        }else{
            return promocaoRepositorio.findAll();
        }
    }

	@PutMapping("/{id}")
	public Promocao update(@RequestBody Promocao promocao, @RequestParam Long id){
		Promocao res = new Promocao();
		Optional<Promocao> promo = promocaoRepositorio.findById(id);
		if(promo.isEmpty()){
			return res;
		}else{
			return promocaoRepositorio.save(promocao);
		}
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id){
		Optional<Promocao> pro = promocaoRepositorio.findById(id);
		if(pro.isEmpty()){
			return "Promoção não encontrada";
		}else{
			promocaoRepositorio.delete(pro.get());
			return "Promoção "+pro.get().getId()+" deletada";
		}
	}

}
