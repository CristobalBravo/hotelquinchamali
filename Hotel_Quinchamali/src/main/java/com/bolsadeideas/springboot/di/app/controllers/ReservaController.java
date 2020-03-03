package com.bolsadeideas.springboot.di.app.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.bolsadeideas.springboot.di.app.models.dao.IReservaHabitacionDao;
import com.bolsadeideas.springboot.di.app.models.entity.*;
import com.bolsadeideas.springboot.di.app.models.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.di.app.paginator.PageRender;

@Controller
@RequestMapping("/admin/reserva")
@SessionAttributes("reserva")
public class ReservaController {
	
	@Autowired
	private IClienteServicies clienteServicies;
	@Autowired
	private IReservaServicies reservaServices;
	@Autowired
	private IPrecioServices precioServices;
	@Autowired
	private IEstadoReservaServices estadoReservaServices;
	@Autowired
	private IHabitacionServices habitacionServices;
	@Autowired
	private IReservaHabitacionDao reservaHbServices;
	
	
	@GetMapping("/crear/{clienteid}")
	public String crear(@PathVariable(value="clienteid") Long clienteid, Map<String, Object> model,RedirectAttributes flash) {
		
		Cliente cliente= clienteServicies.finOne(clienteid);
		if(cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe");
			return "redirect:/admin/reserva/listar";
		}
		
		Reserva reserva = new Reserva();
		reserva.setCliente(cliente);
		model.put("tipos", precioServices.findAll());
		model.put("reserva", reserva);
		model.put("titulo","Crear Reserva");
		
		return"reserva/form";
	}
	

	@RequestMapping(value = { "/listar"}, method = RequestMethod.GET)
	public String listar(Model model) {
		List<Reserva> reserva = reservaServices.findAll();
		model.addAttribute("titulo", "Listado De Reservas");
		model.addAttribute("reservas", reserva);
		return "reserva/listar";
	}
	
	@RequestMapping(value = "/crear", method = RequestMethod.POST)
	public String guardar(@Valid Reserva reserva, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes flash) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Reserva");
			return "reserva/form";
		}
		String mensajeFlash = (reserva.getId() != null) ? "Reserva editada con eéxito" : "Reserva creada con xito";
		reservaServices.save(reserva);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		Long id=reserva.getCliente().getId();
		return "redirect:/admin/reserva/listar";
	}
	
	@RequestMapping(value = "/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Reserva reserva = null;
		if (id > 0) {
			reserva = reservaServices.finOne(id);
		} else {
			flash.addFlashAttribute("error", "Error al editar la reserva.");
			return "redirect:/admin/reserva/listar";

		}
		model.addAttribute("reserva", reserva);
		model.addAttribute("titulo", "Editar Reserva");
		return "reserva/form";
	}

	@RequestMapping(value = "/registrarhb/{id}")
	public String registrarHb(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Reserva reserva = null;
		if (id > 0) {
			reserva = reservaServices.finOne(id);
		} else {
			flash.addFlashAttribute("error", "Error al editar la reserva.");
			return "redirect:/admin/reserva/listar";

		}

		List<Habitacion> habitacionesDisponibles = habitacionServices.findHabitacionDisponible(reserva.getCheckIn(),reserva.getCheckOut());

		if(habitacionesDisponibles.size() == 0 && ((List<ReservaHabitacion>) reservaHbServices.findAll()).size() == 0){
			habitacionesDisponibles = habitacionServices.findAll();
		}
		model.addAttribute("reserva", reserva);
		model.addAttribute("hbdisponible", habitacionesDisponibles);
		model.addAttribute("titulo", "Registrar Habitaciones y Huesped");
		return "reserva/registrarhb";
	}

	@RequestMapping(value = "/registrarhb/save/{id}")
	public String registrarSaveHb(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Reserva reserva = null;
		if (id > 0) {
			reserva = reservaServices.finOne(id);
		} else {
			flash.addFlashAttribute("error", "Error al editar la reserva.");
			return "redirect:/admin/reserva/listar";

		}
		model.addAttribute("reserva", reserva);
		model.addAttribute("titulo", "Registrar Habitaciones y Huesped");
		return "reserva/registrarhb";
	}
	
	@GetMapping(value ="/cargar-tipos_habitacion/{term}",produces = {"application/json"})
	public @ResponseBody List<Habitacion> cargarTiposHabitaciones(@PathVariable String term){
		return clienteServicies.findByNombre(term);
	}
	@RequestMapping(value="/confirmar/{id}")
	public String confirmar (@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Reserva reserva = null;
		if (id > 0) {
			reserva = reservaServices.finOne(id);
		} else {
			flash.addFlashAttribute("error", "Error al editar la reserva.");
			return "redirect:/admin/reserva/listar";

		}
		EstadoReserva estadoReserva = estadoReservaServices.findOne(EstadoReserva.ESTADO_CONFIRMDA);
		reserva.setEstadoReserva(estadoReserva);
		reserva.setLastUpdate(new Date());
		reservaServices.save(reserva);
		flash.addFlashAttribute("success", "Reserva confirmada con éxito.");
		return "redirect:/admin/reserva/listar";
	}
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		Reserva reserva = null;
		if (id > 0) {
			reserva = reservaServices.finOne(id);
		} else {
			flash.addFlashAttribute("error", "Error al eliminar la reserva.");
			return "redirect:/admin/reserva/listar";
		}
		EstadoReserva estadoReserva = estadoReservaServices.findOne(EstadoReserva.ESTADO_ELIMINADA);

		reserva.setEstadoReserva(estadoReserva);
		reserva.setLastUpdate(new Date());
		reservaServices.save(reserva);
		flash.addFlashAttribute("success", "Reserva eliminada con éxito.");
		return "redirect:/admin/reserva/listar";
	}
	
	

}
