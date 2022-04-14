package com.dev.ecommerce.controle;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView listar(@RequestParam(required = false) String msg) {
		ModelAndView mv = new ModelAndView("administrativo/produtos/lista");
		mv.addObject("produtos", produtoRepositorio.findAll());
		mv.addObject("msg", msg);
		return mv;
	}

	@GetMapping("/administrativo/produtos/remover/{id}")
	public String remover(@PathVariable("id") Long id) {
		Optional<Produto> produto = produtoRepositorio.findById(id);
		try{
			produtoRepositorio.delete(produto.get());
		}catch(Exception err){
		}
		return "redirect:/administrativo/produtos/listar";
	}

	@GetMapping("/administrativo/produtos/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Produto> produto = produtoRepositorio.findById(id);
		return cadastrar(produto.get());
	}

	@GetMapping("/administrativo/produtos/arquivar/{id}")
	public String arquivarProduto(@PathVariable("id") Long id) {
		Optional<Produto> produto = produtoRepositorio.findById(id);
		try{
			produtoRepositorio.delete(produto.get());
		}catch(Exception err){
			if(produto.get().getProStatus()==0){
				produto.get().setProStatus(1);
			}else{
				produto.get().setProStatus(0);
			}
			produtoRepositorio.save(produto.get());
			
		}
		return "redirect:/administrativo/produtos/listar";
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
