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

import com.modelo.OrdenDeServicio;
import com.repositorio.OrdenDeServicioRepository;

@RestController
public class OrdenDeServicioController {

    @Autowired
    private OrdenDeServicioRepository ordenDeServicioRepository;

    
    @GetMapping("/ordenes")
    public ResponseEntity<Collection<OrdenDeServicio>> listarOrdenes() {
        try {
            Collection<OrdenDeServicio> ordenes = ordenDeServicioRepository.obtenerTodasLasOrdenes();
            return ResponseEntity.ok(ordenes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
    @PostMapping("/ordenes/new/save")
    public ResponseEntity<String> crearOrden(@RequestBody OrdenDeServicio orden) {
        try {
            ordenDeServicioRepository.insertarOrden(
                orden.getIdOrden(),
                orden.getFecha(),            // Debe ser 'YYYY-MM-DD'
                orden.getServicioPrescrito(),
                orden.getEstado(),
                orden.getMedico(),          // En tu repositorio se mapea a numeroRegistroMedico
                orden.getAfiliado()         // Se mapea a idPaciente
            );
            return new ResponseEntity<>("Orden de servicio creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden de servicio", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @PostMapping("/ordenes/{id}/edit/save")
    public ResponseEntity<String> editarOrden(@PathVariable("id") Long id, @RequestBody OrdenDeServicio orden) {
        try {
            ordenDeServicioRepository.actualizarOrden(
                id,
                orden.getFecha(),
                orden.getServicioPrescrito(),
                orden.getEstado(),
                orden.getMedico(),
                orden.getAfiliado()
            );
            return new ResponseEntity<>("Orden de servicio actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la orden de servicio", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ordenes/{id}/delete")
    public ResponseEntity<String> eliminarOrden(@PathVariable("id") Long id) {
        try {
            ordenDeServicioRepository.eliminarOrden(id);
            return new ResponseEntity<>("Orden de servicio eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la orden de servicio", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
