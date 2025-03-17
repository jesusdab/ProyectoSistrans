package com.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modelo.IPS;
import com.repositorio.IpsRepository;

@RestController
@RequestMapping("/ips")
public class IPSController {

    @Autowired
    private IpsRepository ipsRepository;

    /**
     * GET /ips
     * Devuelve todas las IPS en formato JSON.
     */
    @GetMapping
    public ResponseEntity<Collection<IPS>> listarIPS() {
        try {
            Collection<IPS> listaIPS = ipsRepository.darIPSs();
            return ResponseEntity.ok(listaIPS);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * POST /ips/new/save
     * Crea una nueva IPS. Los datos llegan en el body como JSON.
     */
    @PostMapping("/new/save")
    public ResponseEntity<String> guardarIPS(@RequestBody IPS ips) {
        try {
            ipsRepository.insertarIPS(
                ips.getNit(),
                ips.getTipo(),
                ips.getNombre(),
                ips.getDireccionIPS(),
                ips.getTelefono(),
                ips.getServiciosPrestados()
            );
            return new ResponseEntity<>("IPS creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la IPS", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * POST /ips/{id}/edit/save
     * Actualiza la IPS con el nit = {id}. Los datos llegan como JSON.
     */
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> guardarEdicionIPS(
            @PathVariable("id") Long nit,
            @RequestBody IPS ips) {
        try {
            ipsRepository.actualizarIPS(
                nit,
                ips.getTipo(),
                ips.getNombre(),
                ips.getDireccionIPS(),
                ips.getTelefono(),
                ips.getServiciosPrestados()
            );
            return ResponseEntity.ok("IPS actualizada exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la IPS", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * GET /ips/{id}/delete
     * Elimina la IPS cuyo nit = {id}.
     */
    @GetMapping("/{id}/delete")
    public ResponseEntity<String> eliminarIPS(@PathVariable("id") Long id) {
        try {
            ipsRepository.eliminarIPS(id);
            return ResponseEntity.ok("IPS eliminada exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la IPS", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
