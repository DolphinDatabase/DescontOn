package com.dev.ecommerce.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dev.ecommerce.dto.PromocaoDTO;
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
	public PromocaoDTO create(@RequestBody Promocao promocao){
		 Promocao save = promocaoRepositorio.save(promocao);
		 PromocaoDTO res = new PromocaoDTO(save.getId(),save.getNome(),save.getDataCadastro(),save.getCondicao(),save.getAcao(),save.getStatus());
			return res;
	}

	@GetMapping
    public List<PromocaoDTO> index(@RequestParam(required = false) Long id){
        if(id != null){
            List<PromocaoDTO> res = new ArrayList<>();
            Optional<Promocao> promocao = promocaoRepositorio.findById(id);
            if(promocao.isPresent()){
            	Promocao exist = promocao.get();
                res.add(new PromocaoDTO(exist.getId(),exist.getNome(),exist.getDataCadastro(),exist.getCondicao(),exist.getAcao(),exist.getStatus()));
            }
            return res;
        }else{
        	List<Promocao> find = promocaoRepositorio.findAll();
            List<PromocaoDTO> res = new ArrayList<>();
            find.forEach(promo->{
            	res.add(new PromocaoDTO(promo.getId(),promo.getNome(),promo.getDataCadastro(),promo.getCondicao(),promo.getAcao(),promo.getStatus()));
            });
            return res;
        }
    }

	@PutMapping("/{id}")
	public PromocaoDTO update(@RequestBody Promocao promocao, @RequestParam Long id){
		Optional<Promocao> promo = promocaoRepositorio.findById(id);
		if(promo.isEmpty()){
			return new PromocaoDTO(null, null, null, null, null, null);
		}else{
			Promocao save = promocaoRepositorio.save(promocao);
			return new PromocaoDTO(save.getId(),save.getNome(),save.getDataCadastro(),save.getCondicao(),save.getAcao(),save.getStatus());
			
		}
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id){
		Optional<Promocao> pro = promocaoRepositorio.findById(id);
		if(pro.isEmpty()){
			return "Promoção não encontrada";
		}else{
			try {
				promocaoRepositorio.delete(pro.get());
				return "Promoção"+pro.get().getId()+" deletada.";
			}catch (Exception err) {
				Promocao promocaoSave = pro.get();
				promocaoSave.setStatus(1);
				promocaoRepositorio.save(promocaoSave);
				return "Promoção"+pro.get().getId()+" arquivada.";
			}
		}
	}

}
