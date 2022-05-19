package com.dev.ecommerce.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("administrativo/produto")
public class ProdutoView {
    
    @GetMapping
	public ModelAndView cadastrar(@RequestParam(required = false) Long id) {
		ModelAndView mv = new ModelAndView("administrativo/produtos/cadastro");
		mv.addObject("id", id);
		return mv;
	}

    @GetMapping("/listagem")
	public ModelAndView listar(@RequestParam(required = false) String msg) {
		ModelAndView mv = new ModelAndView("administrativo/produtos/lista");
		mv.addObject("msg", msg);
		return mv;
	}

}
