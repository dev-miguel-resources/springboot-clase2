package com.chilecompra.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chilecompra.springboot.app.models.service.IClienteService;
import com.chilecompra.springboot.app.view.xml.ClienteList;

@RestController
@RequestMapping("/api/clientes") //raiz para las demas rutas
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService; //inyectamos el cliente
	
	@GetMapping(value = "/listar")
	public ClienteList listar() {
		return new ClienteList(clienteService.findAll());
	}
}
