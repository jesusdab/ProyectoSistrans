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

import com.modelo.ServicioDeSalud;
import com.repositorio.ServicioDeSaludRepository;

@RestController
public class ServicioDeSaludController {

    @Autowired
    private ServicioDeSaludRepository servicioDeSaludRepository;

  
    @GetMapping("/servicios")
    public ResponseEntity<Collection<ServicioDeSalud>> listarServicios() {
        try {
            Collection<ServicioDeSalud> servicios = servicioDeSaludRepository.obtenerTodosLosServicios();
            return ResponseEntity.ok(servicios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

 
    @PostMapping("/servicios/new/save")
    public ResponseEntity<String> crearServicio(@RequestBody ServicioDeSalud servicio) {
        try {
            servicioDeSaludRepository.insertarServicio(
                servicio.getIdServicio(),
                servicio.getTipoServicio()
            );
            return new ResponseEntity<>("Servicio de salud creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el servicio de salud", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

  
    @PostMapping("/servicios/{id}/edit/save")
    public ResponseEntity<String> editarServicio(
            @PathVariable("id") Long id,
            @RequestBody ServicioDeSalud servicio
    ) {
        try {
            servicioDeSaludRepository.actualizarServicio(
                id,
                servicio.getTipoServicio()
            );
            return new ResponseEntity<>("Servicio de salud actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el servicio de salud", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

  
    @GetMapping("/servicios/{id}/delete")
    public ResponseEntity<String> eliminarServicio(@PathVariable("id") Long id) {
        try {
            servicioDeSaludRepository.eliminarServicio(id);
            return new ResponseEntity<>("Servicio de salud eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el servicio de salud", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
