package com.bolsadeideas.springboot.di.app.controllers;

import com.bolsadeideas.springboot.di.app.models.dao.IVentaDao;
import com.bolsadeideas.springboot.di.app.models.entity.EstadoReserva;
import com.bolsadeideas.springboot.di.app.models.entity.Reserva;
import com.bolsadeideas.springboot.di.app.models.entity.ReservaHabitacion;
import com.bolsadeideas.springboot.di.app.models.entity.TipoPago;
import com.bolsadeideas.springboot.di.app.models.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/admin/venta")
@SessionAttributes("venta")
public class VentaController {

    @Autowired
    private IVentaDao ventaServices;
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
    @Autowired
    private ITipoPagoServices tipoPagoServices;


    @RequestMapping(value = { "/listar"}, method = RequestMethod.GET)
    public String listar(Model model) {
        List<Reserva> reservasAux = reservaServices.findAll();

        List<Reserva> reservas = new ArrayList<>();

        for (Reserva res : reservasAux) {
            if(res.getEstadoReserva().getId() == EstadoReserva.ESTADO_CONFIRMDA || res.getEstadoReserva().getId() == EstadoReserva.ESTADO_PAGADA){
                reservas.add(res);
            }
        }

        model.addAttribute("titulo", "Ventas");
        model.addAttribute("reservas", reservas);
        return "venta/listar";
    }

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    @RequestMapping(value = { "/detalle/{reservaid}"}, method = RequestMethod.GET)
    public String listar(@PathVariable(value="reservaid") Long reservaid, Map<String, Object> model, RedirectAttributes flash) {
        Reserva reserva = reservaServices.finOne(reservaid);
        List<TipoPago> tipoPagos = tipoPagoServices.findAll();

        if(reserva == null){
            flash.addFlashAttribute("error", "La reserva no existe");
            return "redirect:/admin/venta/listar";
        }

        long dias = getDifferenceDays(reserva.getCheckIn(), reserva.getCheckOut());
        double montoHospedaje = 0;
        for(ReservaHabitacion hb : reserva.getHabitaciones()){
            montoHospedaje += hb.getHabitacion().getTipoHabitacion().getPrecio() * dias;
        }

        model.put("titulo", "Detalle Venta");
        model.put("reserva", reserva);
        model.put("tipoPagos", tipoPagos);
        model.put("dias", dias);
        model.put("montoHospedaje", (long)montoHospedaje);
        model.put("txtbtn", "Pagar");
        return "venta/detalle";
    }
}
