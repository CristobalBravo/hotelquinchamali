package com.bolsadeideas.springboot.di.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.bolsadeideas.springboot.di.app.models.entity.Habitacion;
import com.bolsadeideas.springboot.di.app.models.entity.Reserva;
import com.bolsadeideas.springboot.di.app.models.services.IReservaServicies;

@Controller
@RequestMapping("/habitacion")
@SessionAttributes("habitacion")
class HabitacionController {
	

	@Autowired
	private IReservaServicies reservaServices;
	
	
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
	
}
