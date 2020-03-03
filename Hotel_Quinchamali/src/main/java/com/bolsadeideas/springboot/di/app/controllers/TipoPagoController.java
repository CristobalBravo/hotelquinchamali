package com.bolsadeideas.springboot.di.app.controllers;

import com.bolsadeideas.springboot.di.app.models.dao.ITipoPagoDao;
import com.bolsadeideas.springboot.di.app.models.entity.TipoPago;
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
@RequestMapping("/admin/tipopago")
@SessionAttributes("tipopago")
public class TipoPagoController {

    @Autowired
    private ITipoPagoDao tipoPagoServices;

    @GetMapping("/crear")
    public String crear(Map<String, Object> model) {

        TipoPago tipoPago = new TipoPago();
        model.put("tipopago", tipoPago);
        model.put("txtbtn", "Crear Tipo de Pago");
        model.put("titulo", "Crear Tipo de Pago");

        return"tipopago/form";
    }


    @RequestMapping(value = { "/listar"}, method = RequestMethod.GET)
    public String listar(Model model) {
        List<TipoPago> tipoPagos = (List<TipoPago>) tipoPagoServices.findAll();
        model.addAttribute("titulo", "Listado de Tipo de Pagos");
        model.addAttribute("tipoPagos", tipoPagos);
        return "tipopago/listar";
    }

    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    public String guardar(@Valid TipoPago tipo, BindingResult result, Model model, SessionStatus status,
                          RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Estado");
            return "tipopago/form";
        }
        String mensajeFlash = (tipo.getId() != null) ? "Tipo de pago editado con éxito" : "Tipo de pago creado con éxito";

        tipoPagoServices.save(tipo);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:listar";
    }

    @RequestMapping(value = "/editar/{id}")
    public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
        TipoPago tipo = null;
        if (id > 0) {
            tipo = tipoPagoServices.findById(id).get();
        } else {
            flash.addFlashAttribute("error", "Error al editar el tipo de pago");
            return "redirect:/listar";

        }
        model.addAttribute("tipopago", tipo);
        model.addAttribute("txtbtn", "Editar Estado");
        model.addAttribute("titulo", "Editar Estado");
        return "tipopago/form";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        TipoPago tipoPago = null;
        if (id > 0) {
            tipoPago = tipoPagoServices.findById(id).get();
        } else {
            flash.addFlashAttribute("error", "Error al eliminar el Tipo de Pago.");
            return "redirect:/admin/tipopago/listar";
        }
        tipoPagoServices.deleteById(tipoPago.getId());
        flash.addFlashAttribute("success", "Tipo de Pago eliminada con éxito.");
        return "redirect:/admin/tipopago/listar";
    }


}
