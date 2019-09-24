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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.di.app.models.entity.Cliente;
import com.bolsadeideas.springboot.di.app.models.entity.Reserva;
import com.bolsadeideas.springboot.di.app.models.services.IClienteServicies;
import com.bolsadeideas.springboot.di.app.models.services.IReservaServicies;
import com.bolsadeideas.springboot.di.app.paginator.PageRender;

@Controller
@RequestMapping("/reserva")
@SessionAttributes("reserva")
public class ReservaController {
	
	@Autowired
	private IClienteServicies clienteServicies;
	@Autowired
	private IReservaServicies reservaServices;
	
	
	@GetMapping("/form/{clienteid}")
	public String crear(@PathVariable(value="clienteid") Long clienteid, Map<String, Object> model,RedirectAttributes flash) {
		
		Cliente cliente= clienteServicies.finOne(clienteid);
		if(cliente == null) {
			flash.addFlashAttribute("error", "el cliente no existe");
			return "redirect:/listar";
		}
		
		Reserva reserva = new Reserva();
		reserva.setCliente(cliente);
		reserva.setNumero(reserva.getNumero()+1);
		model.put("reserva", reserva);
		model.put("titulo","Crear reserva");
		
		return"reserva/form";
	}
	

	@RequestMapping(value = { "/listar"}, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable paginacion = PageRequest.of(page, 4);
		Page<Reserva> reserva = reservaServices.findAll(paginacion);
		PageRender<Reserva> pageRender = new PageRender<>("/reserva/listar", reserva);
		model.addAttribute("titulo", "Listado De Reservas");
		model.addAttribute("reservas", reserva);
		model.addAttribute("page", pageRender);
		return "reserva/listar";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Reserva reserva, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes flash) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Reserva");
			return "reserva/form";
		}
		String mensajeFlash = (reserva.getId() != null) ? "Reserva editado con exito" : "Reserva creado con exito";
		reservaServices.save(reserva);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		Long id=reserva.getCliente().getId();
		return "redirect:/ver/"+id;
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Reserva reserva = null;
		if (id > 0) {
			reserva = reservaServices.finOne(id);
		} else {
			flash.addFlashAttribute("error", "error al editar la reserva");
			return "redirect:/listar";

		}
		model.addAttribute("reserva", reserva);
		model.addAttribute("titulo", "Editar Reserva");
		return "form";
	}
	
	

}
