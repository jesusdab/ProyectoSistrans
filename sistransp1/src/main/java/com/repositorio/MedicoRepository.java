package com.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.modelo.Medico;

public interface MedicoRepository extends JpaRepository<Medico, String> {

    @Query(value = "SELECT * FROM MEDICO", nativeQuery = true)
    Collection<Medico> obtenerTodosLosMedicos();

    @Query(value = "SELECT * FROM MEDICO WHERE numeroRegistroMedico = :numeroRegistroMedico", nativeQuery = true)
    Medico obtenerMedicoPorRegistro(@Param("numeroRegistroMedico") String numeroRegistroMedico);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO MEDICO (numeroRegistroMedico, nombre, tipoIdentificacion, numeroIdentificacion, especialidad) VALUES (:numeroRegistroMedico, :nombre, :tipoIdentificacion, :numeroIdentificacion, :especialidad)", nativeQuery = true)
    void insertarMedico(@Param("numeroRegistroMedico") Long numeroRegistroMedico,
                        @Param("nombre") String nombre, 
                        @Param("tipoIdentificacion") String tipoIdentificacion, 
                        @Param("numeroIdentificacion") Long numeroIdentificacion,
                        @Param("especialidad") String especialidad);

    @Modifying
    @Transactional
    @Query(value = "UPDATE MEDICO SET nombre = :nombre, tipoIdentificacion = :tipoIdentificacion, numeroIdentificacion = :numeroIdentificacion, especialidad = :especialidad WHERE numeroRegistroMedico = :numeroRegistroMedico", nativeQuery = true)
    void actualizarMedico(@Param("numeroRegistroMedico") Long numeroRegistroMedico,
                          @Param("nombre") String nombre, 
                          @Param("tipoIdentificacion") String tipoIdentificacion, 
                          @Param("numeroIdentificacion") Long numeroIdentificacion,
                          @Param("especialidad") String especialidad);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM MEDICO WHERE numeroRegistroMedico = :numeroRegistroMedico", nativeQuery = true)
    void eliminarMedico(@Param("numeroRegistroMedico") String numeroRegistroMedico);
}