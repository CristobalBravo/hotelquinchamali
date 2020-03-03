package com.bolsadeideas.springboot.di.app.controllers;

import java.util.List;
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
import com.bolsadeideas.springboot.di.app.models.services.IClienteServicies;
import com.bolsadeideas.springboot.di.app.paginator.PageRender;

@Controller
@RequestMapping("/admin/cliente")
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteServicies clienteServices;

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Cliente cliente = clienteServices.finOne(id);
		if (cliente == null) {
			flash.addFlashAttribute("error", "El Cliente solicitado no existe");
			return "redirect:/listar";

		}
		model.put("cliente", cliente);
		model.put("titulo", "Detalle Cliente : " + cliente.getNombre_completo());
		return "cliente/ver";

	}

	@RequestMapping(value = { "/listar", "/" }, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		List<Cliente> clientes = clienteServices.findAll();
		model.addAttribute("titulo", "Listado De Clientes");
		model.addAttribute("clientes", clientes);
		return "cliente/listar";
	}

	@RequestMapping(value = "/crear") // redireccionamiento a la url
	public String crear(Map<String, Object> model) {

		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("txtbtn", "Crear Cliente");
		model.put("titulo", "Formulario de Cliente");
		return "cliente/form";
	}

	@RequestMapping(value = "/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteServices.finOne(id);
		} else {
			flash.addFlashAttribute("error", "Error al Editar al Cliente.");
			return "redirect:/listar";

		}
		model.addAttribute("cliente", cliente);
		model.addAttribute("txtbtn", "Guardar Cambios del Cliente");
		model.addAttribute("titulo", "Editar Cliente");
		return "cliente/form";
	}

	@RequestMapping(value = "/crear", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes flash) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "cliente/form";
		}
		String mensajeFlash = (cliente.getId() != null) ? "Cliente modificado con éxito." : "Cliente creado con éxito.";

		clienteServices.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			clienteServices.deleted(id);
			flash.addFlashAttribute("success", "Cliente eliminado con éxito.");
		}
		return "redirect:/listar";
	}
}
