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

import com.modelo.Cita;
import com.repositorio.CitaRepository;

@RestController
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

  
    @GetMapping("/citas")
    public ResponseEntity<Collection<Cita>> getCitas() {
        try {
            Collection<Cita> citas = citaRepository.obtenerTodasLasCitas();
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

  
    @PostMapping("/citas/new/save")
    public ResponseEntity<String> createCita(@RequestBody Cita cita) {
        try {
            citaRepository.insertarCita(
                cita.getIdCita(),
                cita.getFecha(),
                cita.getHora(),
                cita.getIpsNit(),
                cita.getIdOrdenServicio(),
                cita.getNumeroRegistroMedico(),
                cita.getIdPacienteAfiliado()
            );
            return new ResponseEntity<>("Cita creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la cita", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

 
    @PostMapping("/citas/{id}/edit/save")
    public ResponseEntity<String> updateCita(@PathVariable("id") long id, @RequestBody Cita cita) {
        try {
            citaRepository.actualizarCita(
                id,
                cita.getFecha(),
                cita.getHora(),
                cita.getIpsNit(),
                cita.getIdOrdenServicio(),
                cita.getNumeroRegistroMedico(),
                cita.getIdPacienteAfiliado()
            );
            return new ResponseEntity<>("Cita actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la cita", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

  
    @GetMapping("/citas/{id}/delete")
    public ResponseEntity<String> deleteCita(@PathVariable("id") long id) {
        try {
            citaRepository.eliminarCita(id);
            return new ResponseEntity<>("Cita eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la cita", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
