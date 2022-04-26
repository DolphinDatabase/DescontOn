package com.dev.ecommerce.controle;

import com.dev.ecommerce.modelos.ItensPromocao;
import com.dev.ecommerce.modelos.Produto;
import com.dev.ecommerce.modelos.Promocao;
import com.dev.ecommerce.repositorios.ItensPromocaoRepositorio;
import com.dev.ecommerce.repositorios.ProdutoRepositorio;
import com.dev.ecommerce.repositorios.PromocaoRepositorio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PromocaoControle {

	@Autowired
	private ProdutoRepositorio repositorioProduto;

	@Autowired
	private PromocaoRepositorio repositorioPromocao;

		
	@Autowired
	private ItensPromocaoRepositorio repositorioItensPromocao;
	

	@GetMapping("/promocao/cadastrar")
	public ModelAndView chamarPromocao(Promocao promocao) {
		ModelAndView mv = new ModelAndView("administrativo/promocoes/promocao");	
		mv.addObject("promocao", promocao);
		mv.addObject("listaItensPromocao", itensPromocao);
		return mv;
	}

	private List<ItensPromocao> itensPromocao = new ArrayList<ItensPromocao>();

	@GetMapping("/adicionarPromocao/{id}")
	public String adicionarPromocao(@PathVariable Long id) {

		Optional<Produto> prod = repositorioProduto.findById(id);
		Produto produto = prod.get();

		int controle = 0;
		for (ItensPromocao it : itensPromocao) {
			if (it.getProduto().getProId().equals(produto.getProId())) {
				controle = 1;
				break;
			}
		}
		if (controle == 0) {
			ItensPromocao item = new ItensPromocao();
			item.setProduto(produto);
			itensPromocao.add(item);

		}
		return "redirect:/promocao/cadastrar";
	}

	@GetMapping("/progressivo/finalizar")
	public ModelAndView finalizarProgressivo(Promocao promocao, BindingResult result) {
		ModelAndView mv = new ModelAndView("administrativo/home");
		repositorioPromocao.saveAndFlush(promocao);
		for (ItensPromocao c : itensPromocao) {
			c.setPromocao(promocao);
			repositorioItensPromocao.saveAndFlush(c);
		}
		itensPromocao = new ArrayList<>();
		promocao = new Promocao();
		return mv;
		
	}

	@PostMapping("/administrativo/promocao/salvar")
	public ModelAndView salvar(Promocao promocao, BindingResult result) {
		
		if (result.hasErrors()) {
			return chamarPromocao(promocao);
		}
		
		return finalizarProgressivo(promocao, result);

	}

	@GetMapping("/removerProdutoPromocao/{id}")
	public String removerProdutoCarrinho(@PathVariable Long id) {

		for (ItensPromocao it : itensPromocao) {
			if (it.getProduto().getProId().equals(id)) {
				itensPromocao.remove(it);
				break;
			}
		}
		return "redirect:/promocao/cadastrar";
	}
}
