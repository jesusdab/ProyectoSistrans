package com.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.modelo.Cita;
import com.modelo.OrdenDeServicio;
import com.repositorio.CitaRepository;
import com.repositorio.OrdenDeServicioRepository;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private OrdenDeServicioRepository ordendeservicioRepository;

    @GetMapping
    public ResponseEntity<Collection<Cita>> getCitas() {
        try {
            Collection<Cita> citas = citaRepository.obtenerTodasLasCitas();
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<String> agendarCita(@RequestBody Cita cita,
                                          @RequestParam(value="Tiposervicio", required=false) String Tiposervicio) {
        try {
            if (cita.getIdOrdenServicio() == null) {
                if (Tiposervicio == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                        .body("Falta indicar el tipo de servicio si no hay orden");
                }
                String stLower = Tiposervicio.toLowerCase();
                if (!stLower.contains("general") && !stLower.contains("urgencia")) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                        .body("Este servicio requiere orden, pero no se especificó");
                }

                citaRepository.insertarCita(
                    cita.getIdCita(),
                    cita.getFecha(),
                    cita.getHora(),
                    cita.getIpsNit(),
                    null, 
                    cita.getNumeroRegistroMedico(),
                    cita.getIdPacienteAfiliado()
                );
                return ResponseEntity.status(HttpStatus.CREATED)
                                    .body("Cita de " + Tiposervicio + " agendada sin orden");
            } else {
                OrdenDeServicio orden = ordendeservicioRepository.obtenerOrdenPorId(cita.getIdOrdenServicio());
                if (orden == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("La orden especificada no existe");
                }

                citaRepository.insertarCita(
                    cita.getIdCita(),
                    cita.getFecha(),
                    cita.getHora(),
                    cita.getIpsNit(),
                    cita.getIdOrdenServicio(),
                    cita.getNumeroRegistroMedico(),
                    cita.getIdPacienteAfiliado()
                );
                return ResponseEntity.status(HttpStatus.CREATED).body("Cita agendada con orden");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Error al agendar la cita: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/edit")
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
            return ResponseEntity.ok("Cita actualizada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al actualizar la cita: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCita(@PathVariable("id") long id) {
        try {
            citaRepository.eliminarCita(id);
            return ResponseEntity.ok("Cita eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al eliminar la cita: " + e.getMessage());
        }
    }

    @PostMapping("/{id}/prestar")
    public ResponseEntity<String> prestarServicio(@PathVariable("id") long id) {
        try {
            if (citaRepository.obtenerCitaPorId(id) == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body("La cita con id=" + id + " no existe");
            }

            citaRepository.marcarCitaRealizada(id);
            return ResponseEntity.ok("Servicio de salud prestado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al registrar la prestación: " + e.getMessage());
        }
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<Collection<Cita>> disponibilidad(@RequestParam("servicio") String servicioPrescrito) {
        try {
            Collection<Cita> citas = citaRepository.citasProximas4SemanasPorServicio(servicioPrescrito);
            return ResponseEntity.ok(citas); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
