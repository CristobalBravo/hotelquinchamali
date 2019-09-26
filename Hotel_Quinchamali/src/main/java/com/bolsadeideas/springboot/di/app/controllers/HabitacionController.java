package com.bolsadeideas.springboot.di.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.di.app.models.dao.IHabitacionDao;
import com.bolsadeideas.springboot.di.app.models.entity.Habitacion;
import com.bolsadeideas.springboot.di.app.models.entity.Reserva;
import com.bolsadeideas.springboot.di.app.models.services.IReservaServicies;
import com.bolsadeideas.springboot.di.app.paginator.PageRender;

@Controller
@RequestMapping("/habitacion")
@SessionAttributes("habitacion")
class HabitacionController {
	

	@Autowired
	private IReservaServicies reservaServices;
	@Autowired
	private IHabitacionDao habitacionServices;
	
	
	@GetMapping("/form/{reservaId}")
	public String crear(@PathVariable(value="reservaId") Long reservaId, Map<String, Object> model,RedirectAttributes flash) {
		Reserva reserva= reservaServices.finOne(reservaId);
		if(reserva == null) {
			flash.addFlashAttribute("error", "el cliente no existe");
			return "redirect:/listar";
		}
		
		Habitacion habitacion = new Habitacion();
		habitacion.setReserva(reserva);
		model.put("habitacion", habitacion);
		model.put("titulo","Ingrese Sus Habitaciones");
		model.put("cantidad", reserva.getCantidadHabitaciones());

		return"habitacion/form";

	}
	
	@RequestMapping(value = { "/listar"}, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable paginacion = PageRequest.of(page, 37);
		Page<Habitacion> habitacion = habitacionServices.findAll(paginacion);
		PageRender<Habitacion> pageRender = new PageRender<>("/habitacion/listar", habitacion);
		model.addAttribute("titulo", "Listado De Habitaciones");
		model.addAttribute("Habitaciones", habitacion);
		model.addAttribute("page", pageRender);
		return "habitacion/listar";
	}
	
}
