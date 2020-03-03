package com.bolsadeideas.springboot.di.app.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.di.app.models.dao.IPrecioDao;
import com.bolsadeideas.springboot.di.app.models.entity.Cliente;
import com.bolsadeideas.springboot.di.app.models.entity.Habitacion;
import com.bolsadeideas.springboot.di.app.models.entity.TipoHabitacion;
import com.bolsadeideas.springboot.di.app.models.services.IHabitacionServices;
import com.bolsadeideas.springboot.di.app.models.services.IPrecioServices;
import com.bolsadeideas.springboot.di.app.paginator.PageRender;

@Controller
@RequestMapping("/admin/habitacion")
@SessionAttributes("habitacion")
class HabitacionController {
	

	@Autowired
	private IHabitacionServices habitacionServices;
	
	@Autowired 
	private IPrecioServices tipoServices;
	
	@RequestMapping(value = "/crear") // redireccionamiento a la url
	public String crear(Map<String, Object> model) {

		model.put("tipos", tipoServices.findAll());
		
		Habitacion habitacion = new Habitacion();
		model.put("habitacion", habitacion);
		model.put("txtbtn", "Crear Habitación");
		model.put("titulo", "Formulario de Tipo de Habitación");
		model.put("txtbtn", "Crear Tipo de Habitación");
		return "habitacion/form";
	}
	
	@RequestMapping(value = { "/listar"}, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		List<Habitacion> habitacion = habitacionServices.findAll();
		model.addAttribute("titulo", "Listado De Habitaciones");
		model.addAttribute("Habitaciones", habitacion);
		return "habitacion/listar";
	}
	
	@RequestMapping(value = "/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Habitacion habitacion = null;
		if (id > 0) {
			habitacion = habitacionServices.finOne(id);
		} else {
			flash.addFlashAttribute("error", "error al editar al cliente");
			return "redirect:/listar";

		}
		model.addAttribute("habitacion", habitacion);
		model.addAttribute ("tipos", tipoServices.findAll());
		model.addAttribute("txtbtn", "Guardar Cambios");
		model.addAttribute("titulo", "Editar Habitacion");
		
		return "habitacion/form";
	}
	
	@GetMapping(value ="/cargar-tipos_habitacion/{term}",produces = {"application/json"})
	public @ResponseBody List<TipoHabitacion> cargarTiposHabitaciones(@PathVariable String term){
		return habitacionServices.findByNombre(term);
	}
	
	@PostMapping("/crear")
	public String guardar (Habitacion habitacion , @RequestParam(name = "buscar_tipo_habitacion" , required=false) Long tipoHabitacionId ,RedirectAttributes flash,SessionStatus status) {

		TipoHabitacion tipoHabitacion= tipoServices.finOne(tipoHabitacionId);
		habitacion.setTipoHabitacion(tipoHabitacion);
		String mensajeFlash = (habitacion.getId() != null) ? "Habitacion editado con exito" : "Habitacion creado con exito";
		habitacionServices.save(habitacion);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			habitacionServices.deleted(id);
			flash.addFlashAttribute("success", "Habitacion eliminado con exito");
		}
		return "redirect:/habitacion/listar";
	}
	
}
