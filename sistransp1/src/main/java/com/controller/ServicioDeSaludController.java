package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.modelo.ServicioDeSalud;
import com.repositorio.ServicioDeSaludRepository;

@RestController
public class ServicioDeSaludController {

    @Autowired
    private ServicioDeSaludRepository servicioDeSaludRepository;

    @GetMapping("/servicios")
    public String servicios(Model model) {
        model.addAttribute("servicios", servicioDeSaludRepository.obtenerTodosLosServicios());
        return model.toString();
    }

    @GetMapping("/servicios/new")
    public String servicioForm(Model model) {
        model.addAttribute("servicio", new ServicioDeSalud());
        return "servicioNuevo";
    }

    @PostMapping("/servicios/new/save")
    public String servicioGuardar(@ModelAttribute ServicioDeSalud servicio) {
        servicioDeSaludRepository.insertarServicio(
                servicio.getIdServicio(),
                servicio.getTipoServicio()
        );
        return "redirect:/servicios";
    }

    @GetMapping("/servicios/{id}/edit")
    public String servicioEditarForm(@PathVariable("id") Long id, Model model) {
        ServicioDeSalud servicio = servicioDeSaludRepository.obtenerServicioPorId(id);
        if (servicio != null) {
            model.addAttribute("servicio", servicio);
            return "servicioEditar";
        } else {
            return "redirect:/servicios";
        }
    }

    @PostMapping("/servicios/{id}/edit/save")
    public String servicioEditarGuardar(@PathVariable("id") Long id, @ModelAttribute ServicioDeSalud servicio) {
        servicioDeSaludRepository.actualizarServicio(
                id,
                servicio.getTipoServicio()
        );
        return "redirect:/servicios";
    }

    @GetMapping("/servicios/{id}/delete")
    public String servicioEliminar(@PathVariable("id") Long id) {
        servicioDeSaludRepository.eliminarServicio(id);
        return "redirect:/servicios";
    }
}
