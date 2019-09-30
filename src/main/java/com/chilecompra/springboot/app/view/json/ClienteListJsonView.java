package com.chilecompra.springboot.app.view.json;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.chilecompra.springboot.app.models.entity.Cliente;

@Component("listar.json")
@SuppressWarnings("unchecked")
public class ClienteListJsonView extends MappingJackson2JsonView {

	@Override
	protected Object filterModel(Map<String, Object> model) {

		model.remove("titulo"); //atributos que no quiero mostrar en mi json
		model.remove("page");

		Page<Cliente> clientes = (Page<Cliente>) model.get("clientes");
		model.remove("clientes"); //no los quiero paginados
		
		model.put("clientes", clientes.getContent()); //muestro el contenido de clientes
		
		return super.filterModel(model);
	}

}
