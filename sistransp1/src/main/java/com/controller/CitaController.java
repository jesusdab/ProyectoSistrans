package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modelo.Cita;
import com.repositorio.CitaRepository;

@RestController
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;
    
    @GetMapping("/citas")
    public String citas(Model model) {
        model.addAttribute("citas", citaRepository.obtenerTodasLasCitas());
        return model.toString();
    }
    
    @GetMapping("/citas/new")
    public String citaForm(Model model) {
        model.addAttribute("cita", new Cita());
        return "citaNueva";
    }
    
    @PostMapping("/citas/new/save")
    public String citaGuardar(@ModelAttribute Cita cita) {
        citaRepository.insertarCita(
            cita.getIdCita(),
            cita.getFecha(),
            cita.getHora(),
            cita.getIpsNit(),
            cita.getIdOrdenServicio(),
            cita.getNumeroRegistroMedico(),
            cita.getIdPacienteAfiliado()
        );
        return "redirect:/citas";
    }
    
    @GetMapping("/citas/{id}/edit")
    public String citaEditarForm(@PathVariable("id") long id, Model model) {
        Cita cita = citaRepository.obtenerCitaPorId(id);
        if (cita != null) {
            model.addAttribute("cita", cita);
            return "citaEditar";
        } else {
            return "redirect:/citas";
        }
    }
    
    @PostMapping("/citas/{id}/edit/save")
    public String citaEditarGuardar(@PathVariable("id") long id, @ModelAttribute Cita cita) {
        citaRepository.actualizarCita(
            id,
            cita.getFecha(),
            cita.getHora(),
            cita.getIpsNit(),
            cita.getIdOrdenServicio(),
            cita.getNumeroRegistroMedico(),
            cita.getIdPacienteAfiliado()
        );
        return "redirect:/citas";
    }
    
    @GetMapping("/citas/{id}/delete")
    public String citaEliminar(@PathVariable("id") long id) {
        citaRepository.eliminarCita(id);
        return "redirect:/citas";
    }
}
