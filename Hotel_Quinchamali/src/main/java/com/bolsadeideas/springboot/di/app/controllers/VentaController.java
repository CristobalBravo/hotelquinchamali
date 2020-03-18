package com.bolsadeideas.springboot.di.app.controllers;

import com.bolsadeideas.springboot.di.app.models.dao.IVentaDao;
import com.bolsadeideas.springboot.di.app.models.entity.*;
import com.bolsadeideas.springboot.di.app.models.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/detalle/save/{id}", method = RequestMethod.POST)
    public String registrarSaveHb(@PathVariable(value = "id") Long id, Model model, HttpServletRequest httpRequest, RedirectAttributes flash) {
        Reserva reserva = null;

        if (id > 0) {
            reserva = reservaServices.finOne(id);
        } else {
            flash.addFlashAttribute("error", "Error al editar la reserva.");
            return "redirect:/admin/reserva/listar";
        }
        long dias = getDifferenceDays(reserva.getCheckIn(), reserva.getCheckOut());
        double montoHospedaje = 0;
        for(ReservaHabitacion hb : reserva.getHabitaciones()){
            montoHospedaje += hb.getHabitacion().getTipoHabitacion().getPrecio() * dias;
        }

        String detallecostoextra = httpRequest.getParameter("detallecostoextra");
        Integer costoextra = Integer.parseInt(httpRequest.getParameter("costoextra"));
        Integer costototal = (int) montoHospedaje + costoextra;
        Long tipoPagoId = Long.parseLong(httpRequest.getParameter("tipopago"));
        TipoPago tipoPago = tipoPagoServices.finOne(tipoPagoId);

        Venta venta = new Venta();
        venta.setCostoExtra(costoextra);
        venta.setCostoHospedaje((int) montoHospedaje);
        venta.setReserva(reserva);
        venta.setFecha(new Date());
        venta.setDetalleCostoExtra(detallecostoextra);
        venta.setTipoPago(tipoPago);
        venta.setMontoTotal(costototal);

        ventaServices.save(venta);

        EstadoReserva estado = estadoReservaServices.findOne(EstadoReserva.ESTADO_PAGADA);

        reserva.setEstadoReserva(estado);
        reservaServices.save(reserva);

        reserva = reservaServices.finOne(id);

        return "redirect:/admin/venta/detallepago/" + reserva.getId();
    }

    @RequestMapping(value = { "/detallepago/{reservaid}"}, method = RequestMethod.GET)
    public String detallepago(@PathVariable(value="reservaid") Long reservaid, Map<String, Object> model, RedirectAttributes flash) {
        Reserva reserva = reservaServices.finOne(reservaid);
        List<TipoPago> tipoPagos = tipoPagoServices.findAll();

        if(reserva == null){
            flash.addFlashAttribute("error", "La reserva no existe");
            return "redirect:/admin/venta/listar";
        }

        long dias = getDifferenceDays(reserva.getCheckIn(), reserva.getCheckOut());

        model.put("titulo", "Detalle Pago");
        model.put("reserva", reserva);
        model.put("dias", dias);
        return "venta/detallepago";
    }
}
