package com.dev.ecommerce.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/administrativo/sacola")
public class SacolaView {
    
    @GetMapping
    public ModelAndView sacola(){
        ModelAndView mv = new ModelAndView("administrativo/compras/sacola");
		return mv;
    }

}
