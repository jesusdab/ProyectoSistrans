package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.modelo.IPS;
import com.repositorio.IpsRepository;

@RestController
@RequestMapping("/ips")
public class IPSController {

    @Autowired
    private IpsRepository ipsRepository;

    @GetMapping
    public String listarIPS(Model model) {
        model.addAttribute("ips", ipsRepository.darIPSs());
        return model.toString();
    }

    @GetMapping("/new")
    public String nuevaIPSForm(Model model) {
        model.addAttribute("ips", new IPS());
        return "ipsNuevo";
    }

    @PostMapping("/new/save")
    public String guardarIPS(@ModelAttribute IPS ips) {
        ipsRepository.insertarIPS(
                ips.getNit(),
                ips.getTipo(),
                ips.getNombre(),
                ips.getDireccionIPS(),
                ips.getTelefono(),
                ips.getServiciosPrestados()
        );
        return "redirect:/ips";
    }

    @GetMapping("/{id}/edit")
    public String editarIPSForm(@PathVariable("id") Long nit, Model model) {
        IPS ips = ipsRepository.darIPS(nit);
        if (ips != null) {
            model.addAttribute("ips", ips);
            return "ipsEditar";
        } else {
            return "redirect:/ips";
        }
    }

    @PostMapping("/{id}/edit/save")
    public String guardarEdicionIPS(@PathVariable("id") Long nit, @ModelAttribute IPS ips) {
        ipsRepository.actualizarIPS(
                ips.getNit(),
                ips.getTipo(),
                ips.getNombre(),
                ips.getDireccionIPS(),
                ips.getTelefono(),
                ips.getServiciosPrestados()
        );
        return "redirect:/ips";
    }

    @GetMapping("/{id}/delete")
    public String eliminarIPS(@PathVariable("id") Long id) {
        ipsRepository.eliminarIPS(id);
        return "redirect:/ips";
    }
}