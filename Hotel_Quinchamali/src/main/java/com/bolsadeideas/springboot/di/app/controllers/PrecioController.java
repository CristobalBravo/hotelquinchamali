package com.bolsadeideas.springboot.di.app.controllers;

import com.bolsadeideas.springboot.di.app.models.entity.TipoHabitacion;
import com.bolsadeideas.springboot.di.app.models.services.IPrecioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/tipo")
@SessionAttributes("tipo")
public class PrecioController {
	
	@Autowired
	private IPrecioServices precioService;
	
	@RequestMapping(value = { "/listar"}, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		List<TipoHabitacion> tipo = precioService.findAll();
		model.addAttribute("titulo", "Listado de tipo y precios de habitaciones");
		model.addAttribute("tipos", tipo);
		return "tipo/listar";
	}
	
	
	@RequestMapping(value = "/crear") // redireccionamiento a la url
	public String crear(Map<String, Object> model) {

		TipoHabitacion tipo = new TipoHabitacion();
		model.put("tipo", tipo);
		model.put("titulo", "Formulario de Tipo de Habitación");
		model.put("txtbtn", "Crear Tipo de Habitación");
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
		model.addAttribute("titulo", "Editar Tipo de Habitación");
		model.addAttribute("txtbtn", "Editar Tipo de Habitación");
		return "tipo/form";
	}

	@RequestMapping(value = "/crear", method = RequestMethod.POST)
	public String guardar(@Valid TipoHabitacion tipo, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes flash) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Tipo de Habitación");
			return "tipo/form";
		}
		String mensajeFlash = (tipo.getId() != null) ? "Tipo editado con éxito" : "Tipo creado con éxito";
		precioService.save(tipo);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			precioService.deleted(id);
			flash.addFlashAttribute("success", "Tipo eliminado con éxito");
		}
		return "redirect:/tipo/listar";
	}
}


