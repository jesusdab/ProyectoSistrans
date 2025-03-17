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

import com.modelo.Afiliado;
import com.repositorio.AfiliadoRepository;

@RestController
public class AfiliadoController {

    @Autowired
    private AfiliadoRepository afiliadoRepository;

    
    @GetMapping("/afiliados")
    public ResponseEntity<Collection<Afiliado>> listarAfiliados() {
        try {
            Collection<Afiliado> afiliados = afiliadoRepository.obtenerTodosLosAfiliados();
            return ResponseEntity.ok(afiliados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
    @PostMapping("/afiliados/new/save")
    public ResponseEntity<String> crearAfiliado(@RequestBody Afiliado afiliado) {
        try {
            afiliadoRepository.insertarAfiliado(
                afiliado.getIdPaciente(),
                afiliado.getNombre(),
                afiliado.getTipoIdentificacion(),
                afiliado.getNumeroIdentificacion(),
                afiliado.getFechaNacimiento(),
                afiliado.getDireccionResidencia(),
                afiliado.getTelefono(),
                afiliado.getEmpresaAfiliado(),
                afiliado.getTipoParentesco(),
                afiliado.getIdContribuyente()
            );
            return new ResponseEntity<>("Afiliado creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el afiliado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   
    @PostMapping("/afiliados/{id}/edit/save")
    public ResponseEntity<String> editarAfiliado(@PathVariable("id") long id, @RequestBody Afiliado afiliado) {
        try {
            afiliadoRepository.actualizarAfiliado(
                id,
                afiliado.getNombre(),
                afiliado.getTipoIdentificacion(),
                afiliado.getNumeroIdentificacion(),
                afiliado.getFechaNacimiento(),
                afiliado.getDireccionResidencia(),
                afiliado.getTelefono(),
                afiliado.getEmpresaAfiliado(),
                afiliado.getTipoParentesco(),
                afiliado.getIdContribuyente()
            );
            return new ResponseEntity<>("Afiliado actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el afiliado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping("/afiliados/{id}/delete")
    public ResponseEntity<String> eliminarAfiliado(@PathVariable("id") long id) {
        try {
            afiliadoRepository.eliminarAfiliado(id);
            return new ResponseEntity<>("Afiliado eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el afiliado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
