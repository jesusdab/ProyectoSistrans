package com.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modelo.Afiliado;
import com.repositorio.AfiliadoRepository;

@RestController
@RequestMapping("/afiliados")
public class AfiliadoController {

    @Autowired
    private AfiliadoRepository afiliadoRepository;

    @GetMapping
    public ResponseEntity<Collection<Afiliado>> listarAfiliados() {
        try {
            Collection<Afiliado> afiliados = afiliadoRepository.obtenerTodosLosAfiliados();
            return ResponseEntity.ok(afiliados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Afiliado> obtenerAfiliadoPorId(@PathVariable("idPaciente") long id) {
        try {
            Afiliado afiliado = afiliadoRepository.obtenerAfiliadoPorId(id);
            if (afiliado != null) {
                return ResponseEntity.ok(afiliado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
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
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Error: El idPaciente ya existe", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el afiliado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
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

    @GetMapping("/{id}/delete")
    public ResponseEntity<String> eliminarAfiliado(@PathVariable("id") long id) {
        try {
            afiliadoRepository.eliminarAfiliado(id);
            return new ResponseEntity<>("Afiliado eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el afiliado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}