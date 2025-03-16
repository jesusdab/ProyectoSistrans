package com.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modelo.Afiliado;
import com.repositorio.AfiliadoRepository;



@RestController
public class AfiliadoController{

    @Autowired
    private AfiliadoRepository afiliadoRepository;

  

    @GetMapping("/afiliados")
    public String afiliados(Model model) {
        model.addAttribute("afiliados",afiliadoRepository.obtenerTodosLosAfiliados());
        return model.toString();
    }

    @GetMapping("/afiliados/new")
    public String afiliadoForm(Model model){
        model.addAttribute("afiliado",new Afiliado());
        return "afiliadoNuevo";
    }

    @PostMapping("/afiliados/new/save")
    public String afiliadoGuardar(@ModelAttribute Afiliado afiliado) {
        afiliadoRepository.insertarAfiliado(afiliado.getIdPaciente(),afiliado.getNombre(),afiliado.getTipoIdentificacion(),
        afiliado.getNumeroIdentificacion(),afiliado.getFechaNacimiento(),afiliado.getDireccionResidencia(),afiliado.getTelefono(),afiliado.getEmpresaAfiliado(),afiliado.getTipoParentesco(),afiliado.getIdContribuyente());

        return "redirect:/afiliados";
        
    }

    @GetMapping("/afiliados/{id}/edit")
    public String afiliadoEditarForm(@PathVariable("id") int id, Model model){
        Afiliado afiliado=afiliadoRepository.obtenerAfiliadoPorId(id);
        if(afiliado != null){
            model.addAttribute("afiliado",afiliado);
            return "afiliadoEditar";
        }
        else{
            return "redirect:/afiliados";
        }
    }

    @PostMapping("/afiliados/{id}/edit/save")
    public String afiliadoEditarGuardar(@PathVariable("id") Long id,@ModelAttribute Afiliado afiliado){
        afiliadoRepository.actualizarAfiliado(afiliado.getIdPaciente(),afiliado.getNombre(),afiliado.getTipoIdentificacion(),
        afiliado.getNumeroIdentificacion(),afiliado.getFechaNacimiento(),afiliado.getDireccionResidencia(),afiliado.getTelefono(),afiliado.getEmpresaAfiliado(),afiliado.getTipoParentesco(),afiliado.getIdContribuyente());

        return "redirect:/afiliados";
    }

    @GetMapping("/bares/{id}/delete")
    public String afiliadoEliminar(@PathVariable("id") Long id) {
        afiliadoRepository.eliminarAfiliado(id);
        return "redirect:/afiliados";
    }
    
        
        
    

    
    
}
