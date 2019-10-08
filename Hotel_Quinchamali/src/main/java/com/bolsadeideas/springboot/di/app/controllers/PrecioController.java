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
import com.bolsadeideas.springboot.di.app.models.entity.TipoHabitacion;
import com.bolsadeideas.springboot.di.app.models.services.IPrecioServices;
import com.bolsadeideas.springboot.di.app.paginator.PageRender;

@Controller
@RequestMapping("/tipo")
@SessionAttributes("tipo")
public class PrecioController {
	
	@Autowired
	private IPrecioServices precioService;
	
	@RequestMapping(value = { "/listar"}, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable paginacion = PageRequest.of(page, 37);
		Page<TipoHabitacion> tipo = precioService.findAll(paginacion);
		PageRender<TipoHabitacion> pageRender = new PageRender<>("/tipo/listar", tipo);
		model.addAttribute("titulo", "Listado de tipo y precios de habitaciones");
		model.addAttribute("tipos", tipo);
		model.addAttribute("page", pageRender);
		return "tipo/listar";
	}
	
	
	@RequestMapping(value = "/crear") // redireccionamiento a la url
	public String crear(Map<String, Object> model) {

		TipoHabitacion tipo = new TipoHabitacion();
		model.put("tipo", tipo);
		model.put("titulo", "Formulario de Tipo Habitacion");
		return "tipo/form";
	}
	
	@RequestMapping(value = "/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		TipoHabitacion tipo = null;
		if (id > 0) {
			tipo = precioService.finOne(id);
		} else {
			flash.addFlashAttribute("error", "error al editar al cliente");
			return "redirect:/listar";

		}
		model.addAttribute("tipo",tipo);
		model.addAttribute("titulo", "Editar Tipo Habitacion");
		return "tipo/form";
	}

	@RequestMapping(value = "/crear", method = RequestMethod.POST)
	public String guardar(@Valid TipoHabitacion tipo, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes flash) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Tipo Habitacion");
			return "tipo/form";
		}
		String mensajeFlash = (tipo.getId() != null) ? "Tipo editado con exito" : "Tipo creado con exito";
		precioService.save(tipo);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			precioService.deleted(id);
			flash.addFlashAttribute("success", "Tipo eliminado con exito");
		}
		return "redirect:/tipo/listar";
	}
}

