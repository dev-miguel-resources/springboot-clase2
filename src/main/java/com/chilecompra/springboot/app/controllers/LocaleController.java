package com.chilecompra.springboot.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocaleController {

	@GetMapping("/locale")
	public String locale(HttpServletRequest request) {
		String ultimaUrl = request.getHeader("referer"); //referencia de la ultima url
		
		return "redirect:".concat(ultimaUrl); //para no perder la ultima url al recargar la pagina
	}
}
                                                                  