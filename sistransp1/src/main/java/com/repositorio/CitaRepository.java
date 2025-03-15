package com.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.modelo.Cita;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    @Query(value = "SELECT * FROM CITA", nativeQuery = true)
    Collection<Cita> obtenerTodasLasCitas();

    @Query(value = "SELECT * FROM CITA WHERE idCita = :idCita", nativeQuery = true)
    Cita obtenerCitaPorId(@Param("idCita") long idCita);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CITA (idCita, fecha, hora, IPS_nit, ORDEN_DE_SERVICIO_idOrden, MEDICO_numeroRegistroMedico, AFILIADO_idPaciente) VALUES (:idCita, TO_DATE(:fecha, 'YYYY-MM-DD'), TO_DATE(:hora, 'HH24:MI'), :ipsNit, :idOrdenServicio, :numeroRegistroMedico, :idPacienteAfiliado)", nativeQuery = true)
    void insertarCita(@Param("idCita") long idCita, 
                      @Param("fecha") String fecha, 
                      @Param("hora") String hora, 
                      @Param("ipsNit") long ipsNit, 
                      @Param("idOrdenServicio") long idOrdenServicio, 
                      @Param("numeroRegistroMedico") String numeroRegistroMedico, 
                      @Param("idPacienteAfiliado") Long idPacienteAfiliado);

    @Modifying
    @Transactional
    @Query(value = "UPDATE CITA SET fecha = TO_DATE(:fecha, 'YYYY-MM-DD'), hora = TO_DATE(:hora, 'HH24:MI'), IPS_nit = :ipsNit, ORDEN_DE_SERVICIO_idOrden = :idOrdenServicio, MEDICO_numeroRegistroMedico = :numeroRegistroMedico, AFILIADO_idPaciente = :idPacienteAfiliado WHERE idCita = :idCita", nativeQuery = true)
    void actualizarCita(@Param("idCita") long idCita, 
                        @Param("fecha") String fecha, 
                        @Param("hora") String hora, 
                        @Param("ipsNit") long ipsNit, 
                        @Param("idOrdenServicio") long idOrdenServicio, 
                        @Param("numeroRegistroMedico") String numeroRegistroMedico, 
                        @Param("idPacienteAfiliado") Long idPacienteAfiliado);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM CITA WHERE idCita = :idCita", nativeQuery = true)
    void eliminarCita(@Param("idCita") long idCita);
}