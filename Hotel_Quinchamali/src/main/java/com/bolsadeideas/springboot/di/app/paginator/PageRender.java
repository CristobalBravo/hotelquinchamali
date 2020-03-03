package com.bolsadeideas.springboot.di.app.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
	private String url;
	private Page <T> page;
	private int totalPaginas;
	private int numElementosPorPagina;
	private int paginaActual;
	private List<pageItem> paginas;
	
	public PageRender(String url, Page<T> page) {
		super();
		this.url = url;
		this.page = page;
		this.paginas= new ArrayList<pageItem>();
		numElementosPorPagina= page.getSize();
		totalPaginas=page.getTotalPages();
		paginaActual=page.getNumber()+1;
		
		int desde,hasta;
		if (totalPaginas<= numElementosPorPagina) {
			desde=1;
			hasta= totalPaginas;
		}else{
			if(paginaActual<= numElementosPorPagina/2) {
				desde=1;
				hasta=numElementosPorPagina;
			}else {
				if(paginaActual>= totalPaginas-numElementosPorPagina/2){
					desde=totalPaginas-numElementosPorPagina+1;
					hasta= numElementosPorPagina;
					
				}else {
					desde=paginaActual-numElementosPorPagina/2;
					hasta=numElementosPorPagina;
				}
			}
		}
		for (int i = desde; i < hasta; i++) {
			paginas.add(new pageItem(desde+i, paginaActual==desde+i));
			
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

	public List<pageItem> getPaginas() {
		return paginas;
	}
	
	public Boolean isFirst() {
		return page.isFirst();
	}
	public Boolean isLast() {
		return page.isLast();
	}
	public Boolean isHasNext() {
		return page.hasNext();
	}
	public Boolean isHasPrevious() {
		return page.hasPrevious();
	}
	
	
	
	
	
}
