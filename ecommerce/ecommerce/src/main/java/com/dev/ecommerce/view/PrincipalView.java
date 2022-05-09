package com.dev.ecommerce.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalView {
    @GetMapping("/")
	public String acessarPrincipal() {
		return "home";
	}
}
