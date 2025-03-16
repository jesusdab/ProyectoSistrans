package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.modelo.Medico;
import com.repositorio.MedicoRepository;

@RestController
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping("/medicos")
    public String medicos(Model model) {
        model.addAttribute("medicos", medicoRepository.obtenerTodosLosMedicos());
        return model.toString();
    }

    @GetMapping("/medicos/new")
    public String medicoForm(Model model) {
        model.addAttribute("medico", new Medico());
        return "medicoNuevo";
    }

    @PostMapping("/medicos/new/save")
    public String medicoGuardar(@ModelAttribute Medico medico) {
        medicoRepository.insertarMedico(
                medico.getNumeroRegistroMedico(),
                medico.getNombre(),
                medico.getTipoIdentificacion(),
                medico.getNumeroIdentificacion(),
                medico.getEspecialidad()
        );
        return "redirect:/medicos";
    }

    @GetMapping("/medicos/{registro}/edit")
    public String medicoEditarForm(@PathVariable("registro") String registro, Model model) {
        Medico medico = medicoRepository.obtenerMedicoPorRegistro(registro);
        if (medico != null) {
            model.addAttribute("medico", medico);
            return "medicoEditar";
        } else {
            return "redirect:/medicos";
        }
    }

    @PostMapping("/medicos/{registro}/edit/save")
    public String medicoEditarGuardar(@PathVariable("registro") Long registro, @ModelAttribute Medico medico) {
        medicoRepository.actualizarMedico(
                registro,
                medico.getNombre(),
                medico.getTipoIdentificacion(),
                medico.getNumeroIdentificacion(),
                medico.getEspecialidad()
        );
        return "redirect:/medicos";
    }

    @GetMapping("/medicos/{registro}/delete")
    public String medicoEliminar(@PathVariable("registro") String registro) {
        medicoRepository.eliminarMedico(registro);
        return "redirect:/medicos";
    }
}