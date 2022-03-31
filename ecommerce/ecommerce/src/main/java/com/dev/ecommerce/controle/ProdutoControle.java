package com.dev.ecommerce.controle;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import com.dev.ecommerce.modelos.Produto;
import com.dev.ecommerce.repositorios.ProdutoRepositorio;

@Controller
public class ProdutoControle {

	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	
	@GetMapping("/administrativo/produtos/cadastrar")
	public ModelAndView cadastrar(Produto produto) {
		ModelAndView mv = new ModelAndView("administrativo/produtos/cadastro");
		mv.addObject("produto", produto);
		return mv;
	}
	
	@GetMapping("/administrativo/produtos/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/produtos/lista");
		mv.addObject("listaProdutos", produtoRepositorio.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/produtos/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Produto> produto = produtoRepositorio.findById(id);
		produtoRepositorio.delete(produto.get());
		return listar();
	}
	
	@PostMapping("/administrativo/produtos/salvar")
	public ModelAndView salvar(Produto produto, BindingResult result) {

		if (result.hasErrors()) {
			return cadastrar(produto);
		}
		produtoRepositorio.saveAndFlush(produto);
		return cadastrar(new Produto());
	}

}
