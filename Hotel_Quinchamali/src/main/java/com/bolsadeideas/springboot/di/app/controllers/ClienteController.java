package com.bolsadeideas.springboot.di.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.di.app.models.entity.Cliente;
import com.bolsadeideas.springboot.di.app.models.services.IClienteServicies;
import com.bolsadeideas.springboot.di.app.paginator.PageRender;


@Controller
@SessionAttributes("clientes")
public class ClienteController {
	
	@Autowired
	private IClienteServicies clienteServices;

	@RequestMapping(value = {"/listar","/"}, method = RequestMethod.GET)
	public String listar(@RequestParam (name= "page",defaultValue = "0" ) int page,Model model) {
		Pageable paginacion = PageRequest.of(page, 4);
		Page <Cliente> clientes= clienteServices.findAll(paginacion);
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page",pageRender);
		return "listar";
	}
	
	@RequestMapping(value = "/form") // redireccionamiento a la url
	public String crear(Map<String, Object> model) {

		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}
	
	@RequestMapping (value="/form/{id}") 
	public String editar (@PathVariable(value="id") Long id,Model model ,RedirectAttributes flash) {
		Cliente cliente = null;
		if(id > 0 ) {
			cliente = clienteServices.finOne(id);
		}else {
			flash.addFlashAttribute("error", "error al editar al cliente");
			return "redirect:/listar";
			
		}
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Editar Cliente");
		return "form";
	}
	
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}
		String mensajeFlash=(cliente.getId()!=null)?"cliente editado con exito" : "cliente creado con exito" ;
		
		clienteServices.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}
	@RequestMapping (value="/eliminar/{id}")
	public String eliminar(@PathVariable (value="id") Long id, RedirectAttributes flash) {
		if(id>0 ) {
			clienteServices.deleted(id);
			flash.addFlashAttribute("success", "cliente eliminado con exito");
		}
		return "redirect:/listar";
	}
}
