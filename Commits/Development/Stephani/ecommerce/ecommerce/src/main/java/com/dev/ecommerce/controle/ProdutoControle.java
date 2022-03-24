package com.dev.ecommerce.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProdutoControle {

	
	@GetMapping("/administrativo/produtos/cadastrar")
	public String cadastrar() {
		return "administrativo/produtos/cadastro";

	}
	
	@GetMapping("/produtos/cadastrar")
	public String acessoteste() {
		return "administrativo/produtos/cadastro";

	}
}
