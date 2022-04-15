package com.dev.ecommerce.controle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;


import com.dev.ecommerce.modelos.Compra;
import com.dev.ecommerce.modelos.ItensCompra;
import com.dev.ecommerce.modelos.Produto;
import com.dev.ecommerce.repositorios.CompraRepositorio;
import com.dev.ecommerce.repositorios.ItensCompraRepositorio;
import com.dev.ecommerce.repositorios.ProdutoRepositorio;


@Controller
public class SacolaControle {

	@Autowired
	private ProdutoRepositorio repositorioProduto;

	@Autowired
	private CompraRepositorio repositorioCompra;

	@Autowired
	private ItensCompraRepositorio repositorioItensCompra;

	private List<ItensCompra> itensCompra = new ArrayList<ItensCompra>();
	private Compra compra = new Compra();

	private void calcularTotal() {
		compra.setComValorTotal(0.);
		for (ItensCompra it : itensCompra) {
			compra.setComValorTotal(compra.getComValorTotal() + it.getIteValorTotal());
		}
	}

	@GetMapping("/sacola")
	public ModelAndView chamarSacola() {
		ModelAndView mv = new ModelAndView("administrativo/compras/sacola");
		calcularTotal();
		mv.addObject("compra", compra);
		mv.addObject("listaItens", itensCompra);
		return mv;
	}

	@GetMapping("/alterarQuantidade/{id}/{acao}")
	public String alterarQuantidade(@PathVariable Long id, @PathVariable Integer acao) {
		for (ItensCompra it : itensCompra) {
			if (it.getProduto().getProId().equals(id)) {
				if (acao.equals(1)) {
					it.setIteQuantidade(it.getIteQuantidade() + 1);
					it.setIteValorTotal(0.);
					it.setIteValorTotal(it.getIteValorTotal() + (it.getIteQuantidade() * it.getIteValorUnitario()));
				} else if (acao == 0) {
					it.setIteQuantidade(it.getIteQuantidade() - 1);
					it.setIteValorTotal(0.);
					it.setIteValorTotal(it.getIteValorTotal() + (it.getIteQuantidade() * it.getIteValorUnitario()));
				}
				break;
			}
		}
		return "redirect:/sacola";
	}
	@GetMapping("/removerProduto/{id}")
	public String removerProdutoCarrinho(@PathVariable Long id) {

		for (ItensCompra it : itensCompra) {
			if (it.getProduto().getProId().equals(id)) {
				itensCompra.remove(it);
				break;
			}
		}
		return "redirect:/sacola";
	}

	@GetMapping("/adicionarSacola/{id}")
	public String adicionarCarrinho(@PathVariable Long id) {

		Optional<Produto> prod = repositorioProduto.findById(id);
		Produto produto = prod.get();

		int controle = 0;
		for (ItensCompra it : itensCompra) {
			if (it.getProduto().getProId().equals(produto.getProId())) {
				it.setIteQuantidade(it.getIteQuantidade()+1);
				it.setIteValorTotal(0.);
				it.setIteValorTotal(it.getIteValorTotal() + (it.getIteQuantidade() * it.getIteValorUnitario()));
				controle = 1;
				break;
			}
		}
		if (controle == 0) {
			ItensCompra item = new ItensCompra();
			item.setProduto(produto);
			item.setIteValorUnitario(produto.getProValor());
			item.setIteQuantidade(+ 1);
			item.setIteValorTotal(item.getIteValorTotal() + (item.getIteQuantidade() * item.getIteValorUnitario()));
			itensCompra.add(item);
		}
		return "redirect:/sacola";
	}

	@GetMapping("/finalizar")
	public ModelAndView finalizarCompra() {
		Map<String,String> lista = verificarCompra();
		ModelAndView mv;
		if(lista.isEmpty()){
			mv = new ModelAndView("administrativo/home");
			repositorioCompra.saveAndFlush(compra);
			for (ItensCompra c : itensCompra) {
				c.setCompra(compra);
				repositorioItensCompra.saveAndFlush(c);
			}
			itensCompra = new ArrayList<>();
			compra = new Compra();
			mv.addObject("msg", true);
		}else{
			mv = new ModelAndView("administrativo/compras/sacola");
			mv.addObject("errors", lista);
			mv.addObject("compra", compra);
			mv.addObject("listaItens", itensCompra);
		    itensCompra.clear();
		    compra.setComValorTotal(0.0);
		    
		}
		return mv;
	}

	public Map<String,String> verificarCompra(){
		Map<String,String> listaVerificada = new HashMap<String,String>();
		itensCompra.forEach(item->{
			Produto itemCompra = item.getProduto();
			String msg = "";
			Optional<Produto> prd = repositorioProduto.findById(itemCompra.getProId());
			if(prd.isPresent()){
				if(prd.get().getProStatus()==0){
					if(prd.get().getProValor().equals(itemCompra.getProValor())){
						if(!prd.get().getProNome().equals(itemCompra.getProNome())){
							msg = "O nome do produto foi alterado";
						}
					}else{
						msg = "O valor do produto foi alterado";
					}
				}else{
					msg = "Produto não está disponível";
				}
			}else{
				msg = "Produto não existe";
			}
			if(!msg.equals("")){
				listaVerificada.put(itemCompra.getProNome(), msg);
			}
		});
		return listaVerificada;
	}
}