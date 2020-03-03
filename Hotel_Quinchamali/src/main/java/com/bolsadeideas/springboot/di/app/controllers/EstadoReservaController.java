package com.bolsadeideas.springboot.di.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.bolsadeideas.springboot.di.app.models.entity.EstadoReserva;
import com.bolsadeideas.springboot.di.app.models.services.IEstadoReservaServices;


@Controller
@SessionAttributes("estado")
@RequestMapping("/admin/estado")
public class EstadoReservaController {
	
	@Autowired
	private IEstadoReservaServices estadoReservaServices;
	
	@RequestMapping(value = "/crear") // redireccionamiento a la url
	public String crear(Map<String, Object> model) {

		EstadoReserva estadoReserva = new EstadoReserva();
		model.put("estadoReserva", estadoReserva);
		model.put("titulo", "Formulario de Estados de Reserva");
		return "estado/form";
		
	}	
	@RequestMapping(value = { "/listar"}, method = RequestMethod.GET)
	public String listar(Map<String, Object> model) {
		
		List<EstadoReserva> estados = estadoReservaServices.findAll();
		model.put("titulo", "Listado De Estado");
		model.put("estados", estados);
		return "estado/listar";
	}
	
	@RequestMapping(value = "/crear", method = RequestMethod.POST)
	public String guardar(@Valid EstadoReserva estado, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes flash) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Estado");
			return "estado/form";
		}
		String mensajeFlash = (estado.getId() != null) ? "cliente editado con exito" : "cliente creado con exito";

		estadoReservaServices.save(estado);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}
	
	@RequestMapping(value = "/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		EstadoReserva estado = null;
		if (id > 0) {
			estado = estadoReservaServices.findOne(id);
		} else {
			flash.addFlashAttribute("error", "error al editar el estado");
			return "redirect:/listar";

		}
		model.addAttribute("estadoReserva", estado);
		model.addAttribute("titulo", "Editar Estado");
		return "estado/form";
	}

}
