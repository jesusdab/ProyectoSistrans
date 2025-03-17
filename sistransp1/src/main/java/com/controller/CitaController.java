package com.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.modelo.Cita;
import com.modelo.OrdenDeServicio;
import com.repositorio.CitaRepository;
import com.repositorio.OrdenDeServicioRepository;

@RestController
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private OrdenDeServicioRepository ordendeservicioRepository;

  
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


    @PostMapping("/citas/{id}/prestar")
    public ResponseEntity<String> prestarServicio(@PathVariable("id") long id) {
    try {
        if(citaRepository.obtenerCitaPorId(id)==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La cita con id ="+id+"no existe");
          }

        citaRepository.marcarCitaRealizada(id);
        return ResponseEntity.ok("Servicio de salud prestado exitosamente");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Error al registrar la prestación: " + e.getMessage());
    }
}


     @GetMapping("/citas/disponibilidad")
    public ResponseEntity<Collection<Cita>> disponibilidad(
        @RequestParam("servicio") String servicioPrescrito
    ) {
        try {
            
            Collection<Cita> citas = citaRepository.citasProximas4SemanasPorServicio(servicioPrescrito);
            return ResponseEntity.ok(citas); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/citas/new/save")
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
            }
            else {
                
                OrdenDeServicio orden = ordendeservicioRepository.obtenerOrdenPorId(cita.getIdOrdenServicio());
                if (orden == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                        .body("La orden especificada no existe");
                }
                
                String serv = orden.getServicioPrescrito().toLowerCase();
                if (serv.contains("general") || serv.contains("urgencia")) {
                    
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

}
