package com.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.modelo.Medico;
import com.repositorio.MedicoRepository;

@RestController
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;


    @GetMapping("/medicos")
    public ResponseEntity<Collection<Medico>> listarMedicos() {
        try {
            Collection<Medico> medicos = medicoRepository.obtenerTodosLosMedicos();
            return ResponseEntity.ok(medicos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/medicos/new/save")
    public ResponseEntity<String> crearMedico(@RequestBody Medico medico) {
        try {
            medicoRepository.insertarMedico(
                medico.getNumeroRegistroMedico(),
                medico.getNombre(),
                medico.getTipoIdentificacion(),
                medico.getNumeroIdentificacion(),
                medico.getEspecialidad()
            );
            return new ResponseEntity<>("Médico creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el médico", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   
    @PostMapping("/medicos/{registro}/edit/save")
    public ResponseEntity<String> editarMedico(
            @PathVariable("registro") Long registro,
            @RequestBody Medico medico
    ) {
        try {
            medicoRepository.actualizarMedico(
                registro,
                medico.getNombre(),
                medico.getTipoIdentificacion(),
                medico.getNumeroIdentificacion(),
                medico.getEspecialidad()
            );
            return new ResponseEntity<>("Médico actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el médico", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/medicos/{registro}/delete")
    public ResponseEntity<String> eliminarMedico(@PathVariable("registro") String registro) {
        try {
            medicoRepository.eliminarMedico(registro);
            return new ResponseEntity<>("Médico eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el médico", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
