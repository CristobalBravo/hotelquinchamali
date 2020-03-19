package com.bolsadeideas.springboot.di.app.controllers;

import com.bolsadeideas.springboot.di.app.models.dao.IReservaHabitacionDao;
import com.bolsadeideas.springboot.di.app.models.entity.*;
import com.bolsadeideas.springboot.di.app.models.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


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
	private IReservaHabitacionServices reservaHbServices;
	@Autowired
	private IHuespedServices huespedServices;
	
	
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
		
		return "reserva/form";
	}
	

	@RequestMapping(value = { "/listar"}, method = RequestMethod.GET)
	public String listar(Model model) {
		List<Reserva> reservas = reservaServices.findAll();

		for(Reserva reserva : reservas){
			Long id = reserva.getId();
			if(reserva.getHabitaciones().isEmpty()){
				for (int i = 0; i < reserva.getCantidadHabitaciones(); i++){
					ReservaHabitacion hb = new ReservaHabitacion();
					hb.setReserva(reserva);
					hb.setCheck_in(reserva.getCheckIn());
					hb.setCheck_out(reserva.getCheckOut());
					reservaHbServices.save(hb);
				}
			}else if(reserva.getHabitaciones().size() != reserva.getCantidadHabitaciones()){
				if(reserva.getHabitaciones().size() < reserva.getCantidadHabitaciones()){
					int tope = reserva.getCantidadHabitaciones() - reserva.getHabitaciones().size();
					for (int i = 0; i < tope; i++){
						ReservaHabitacion hb = new ReservaHabitacion();
						hb.setReserva(reserva);
						hb.setCheck_in(reserva.getCheckIn());
						hb.setCheck_out(reserva.getCheckOut());
						reservaHbServices.save(hb);
					}
				}else{
					int exceso =  reserva.getHabitaciones().size() - reserva.getCantidadHabitaciones();
					for (int i = 0; i < exceso; i++){
						reserva = reservaServices.finOne(id);
						ReservaHabitacion hb = reserva.getHabitaciones().get(reserva.getHabitaciones().size()-1);
						reservaHbServices.deleted(hb.getId());
					}
				}
			}
		}

		model.addAttribute("titulo", "Listado De Reservas");
		model.addAttribute("reservas", reservas);
		return "reserva/listar";
	}
	
	@RequestMapping(value = "/crear", method = RequestMethod.POST)
	public String guardar(@Valid Reserva reserva, BindingResult result, Model model,
			RedirectAttributes flash) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Reserva");
			return "reserva/form";
		}
		String mensajeFlash = (reserva.getId() != null) ? "Reserva editada con éxito" : "Reserva creada con éxito";
		EstadoReserva estado = estadoReservaServices.findOne(EstadoReserva.ESTADO_ACTIVA);
		reserva.setEstadoReserva(estado);
		reserva.setLastUpdate(new Date());
		if(reserva.getId() == null){
			reserva.setFecha(new Date());
		}

		reserva = reservaServices.save(reserva);

		Long id = reserva.getId();
		reserva = reservaServices.finOne(id);

		for (int i = 0; i < reserva.getCantidadHabitaciones(); i++){
			ReservaHabitacion hb = new ReservaHabitacion();
			hb.setReserva(reserva);
			hb.setCheck_in(reserva.getCheckIn());
			hb.setCheck_out(reserva.getCheckOut());
			reservaHbServices.save(hb);
		}

		flash.addFlashAttribute("success", mensajeFlash);
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

	@RequestMapping(value = "/registrarhb/{id}", method = RequestMethod.GET)
	public String registrarHb(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Reserva reserva = null;
		if (id > 0) {
			reserva = reservaServices.finOne(id);
		} else {
			flash.addFlashAttribute("error", "Error al editar la reserva.");
			return "redirect:/admin/reserva/listar";

		}

		if(reserva.getHabitaciones().isEmpty()){
			for (int i = 0; i < reserva.getCantidadHabitaciones(); i++){
				ReservaHabitacion hb = new ReservaHabitacion();
				hb.setReserva(reserva);
				hb.setCheck_in(reserva.getCheckIn());
				hb.setCheck_out(reserva.getCheckOut());
				reservaHbServices.save(hb);
			}
		}else if(reserva.getHabitaciones().size() != reserva.getCantidadHabitaciones()){
			if(reserva.getHabitaciones().size() < reserva.getCantidadHabitaciones()){
				int tope = reserva.getCantidadHabitaciones() - reserva.getHabitaciones().size();
				for (int i = 0; i < tope; i++){
					ReservaHabitacion hb = new ReservaHabitacion();
					hb.setReserva(reserva);
					hb.setCheck_in(reserva.getCheckIn());
					hb.setCheck_out(reserva.getCheckOut());
					reservaHbServices.save(hb);
				}
			}else{
				int exceso =  reserva.getHabitaciones().size() - reserva.getCantidadHabitaciones();
				for (int i = 0; i < exceso; i++){
					reserva = reservaServices.finOne(id);
					ReservaHabitacion hb = reserva.getHabitaciones().get(reserva.getHabitaciones().size()-1);
					reservaHbServices.deleted(hb.getId());
				}
			}
		}
		reserva = reservaServices.finOne(id);
		List<Habitacion> habitacionesDisponibles = habitacionServices.findAll();

		reserva = reservaServices.finOne(id);


		model.addAttribute("reserva", reserva);
		model.addAttribute("list", -1);
		model.addAttribute("hbreservadas", reserva.getHabitaciones());
		model.addAttribute("hbdisponible", habitacionesDisponibles);
		model.addAttribute("titulo", "Registrar Habitaciones y Huesped");
		flash.addFlashAttribute("success", "Datos registrados correctamente");
		return "reserva/registrarhb";
	}

	@RequestMapping(value = "/registrarhb/save/{id}", method = RequestMethod.POST)
	public String registrarSaveHb(@PathVariable(value = "id") Long id, Model model, HttpServletRequest httpRequest, RedirectAttributes flash) {
		Reserva reserva = null;

		if (id > 0) {
			reserva = reservaServices.finOne(id);
		} else {
			flash.addFlashAttribute("error", "Error al editar la reserva.");
			return "redirect:/admin/reserva/listar";
		}

		long idHb = 0;
		int canthpd = 0;
		int canthpdCurrent = 0;
		ReservaHabitacion hb;

		for (ReservaHabitacion hbaux : reserva.getHabitaciones()){
			long idRH = hbaux.getId();
			hb = reservaHbServices.finOne(idRH);
			idHb = Long.parseLong(httpRequest.getParameter("habitacion"+hb.getId()));
			Habitacion ht = habitacionServices.finOne(idHb);
			hb.setHabitacion(ht);

			canthpd = Integer.parseInt(httpRequest.getParameter("canthpd"+hb.getId()));
			canthpdCurrent = hb.getHuespedes().size();

			if(canthpdCurrent == 0){
				for(int j = 1; j <= canthpd; j++){
					Huesped huesped = new Huesped();
					huesped.setCi(httpRequest.getParameter("ci"+j));
					huesped.setNombreCompleto(httpRequest.getParameter("nombre"+j));
					huesped.setReservaHabitacion(hb);
					huespedServices.save(huesped);
				}
			}else if(canthpd == canthpdCurrent){

			}else if(canthpd < canthpdCurrent){

			}else if(canthpd > canthpdCurrent){

			}



			reservaHbServices.save(hb);
		}

		return "redirect:/admin/reserva/registrarhb/" + reserva.getId();
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
