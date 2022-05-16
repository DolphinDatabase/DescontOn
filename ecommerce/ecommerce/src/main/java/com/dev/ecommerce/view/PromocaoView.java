package com.dev.ecommerce.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/administrativo/promocao")
public class PromocaoView {
    
    @GetMapping("/listagem")
    public ModelAndView promocao(){
        ModelAndView mv = new ModelAndView("administrativo/promocao/lista");
        return mv;
    }

    @GetMapping
    public ModelAndView cadastro(@RequestParam(required = false) Long id){
        ModelAndView mv = new ModelAndView("administrativo/promocao/cadastro");
        mv.addObject("id", id);
        return mv;
    }

}
