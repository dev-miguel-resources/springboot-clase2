package com.chilecompra.springboot.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;
	private Page<T> page;

	private int totalPaginas;

	private int numElementosPorPagina;

	private int paginaActual;

	private List<PageItem> paginas;

	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<PageItem>();

		numElementosPorPagina = 6;
		totalPaginas = page.getTotalPages();
		paginaActual = page.getNumber() + 1;

		int desde, hasta;
		if (totalPaginas <= numElementosPorPagina) { //muestra todo el paginador completo
			desde = 1;
			hasta = totalPaginas;
		} else {
			if (paginaActual <= numElementosPorPagina / 2) { //navego por rangos, ni al inicio ni al final
				desde = 1;
				hasta = numElementosPorPagina;
			} else if (paginaActual >= totalPaginas - numElementosPorPagina / 2) { //de donde estoy hasta rango final
				desde = totalPaginas - numElementosPorPagina + 1;
				hasta = numElementosPorPagina;
			} else {
				desde = paginaActual - numElementosPorPagina / 2; //rango intermedio
				hasta = numElementosPorPagina;
			}
		}

		for (int i = 0; i < hasta; i++) {
			paginas.add(new PageItem(desde + i, paginaActual == desde + i)); //recorriendo y rellenando mi paginador
		}

	}

	public String getUrl() {
		return url;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}

	public boolean isFirst() {
		return page.isFirst();
	}

	public boolean isLast() {
		return page.isLast();
	}

	public boolean isHasNext() {
		return page.hasNext();
	}

	public boolean isHasPrevious() {
		return page.hasPrevious();
	}

}
