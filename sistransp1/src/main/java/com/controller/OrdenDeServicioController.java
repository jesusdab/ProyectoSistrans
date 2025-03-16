package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.modelo.OrdenDeServicio;
import com.repositorio.OrdenDeServicioRepository;

@RestController
public class OrdenDeServicioController {

    @Autowired
    private OrdenDeServicioRepository ordenDeServicioRepository;

    @GetMapping("/ordenes")
    public String ordenes(Model model) {
        model.addAttribute("ordenes", ordenDeServicioRepository.obtenerTodasLasOrdenes());
        return model.toString();
    }

    @GetMapping("/ordenes/new")
    public String ordenForm(Model model) {
        model.addAttribute("orden", new OrdenDeServicio());
        return "ordenNuevo";
    }

    @PostMapping("/ordenes/new/save")
    public String ordenGuardar(@ModelAttribute OrdenDeServicio orden) {
        ordenDeServicioRepository.insertarOrden(
                orden.getIdOrden(),
                orden.getFecha(),
                orden.getServicioPrescrito(),
                orden.getEstado(),
                orden.getMedico(),
                orden.getAfiliado()
        );
        return "redirect:/ordenes";
    }

    @GetMapping("/ordenes/{id}/edit")
    public String ordenEditarForm(@PathVariable("id") long id, Model model) {
        OrdenDeServicio orden = ordenDeServicioRepository.obtenerOrdenPorId(id);
        if (orden != null) {
            model.addAttribute("orden", orden);
            return "ordenEditar";
        } else {
            return "redirect:/ordenes";
        }
    }

    @PostMapping("/ordenes/{id}/edit/save")
    public String ordenEditarGuardar(@PathVariable("id") Long id, @ModelAttribute OrdenDeServicio orden) {
        ordenDeServicioRepository.actualizarOrden(
                id,
                orden.getFecha(),
                orden.getServicioPrescrito(),
                orden.getEstado(),
                orden.getMedico(),
                orden.getAfiliado()
        );
        return "redirect:/ordenes";
    }

    @GetMapping("/ordenes/{id}/delete")
    public String ordenEliminar(@PathVariable("id") Long id) {
        ordenDeServicioRepository.eliminarOrden(id);
        return "redirect:/ordenes";
    }
}