package com.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.modelo.Afiliado;

public interface AfiliadoRepository extends JpaRepository<Afiliado, Long> {

    @Query(value = "SELECT * FROM AFILIADO", nativeQuery = true)
    Collection<Afiliado> obtenerTodosLosAfiliados();

    @Query(value = "SELECT * FROM AFILIADO WHERE idPaciente = :idPaciente", nativeQuery = true)
    Afiliado obtenerAfiliadoPorId(@Param("idPaciente") long idPaciente);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO AFILIADO (idPaciente, nombre, tipoIdentificacion, numeroIdentificacion, fechaNacimiento, direccionResidencia, telefono, empresaAfiliado, tipoParentesco, AFILIADO_idPaciente) VALUES (:idPaciente, :nombre, :tipoIdentificacion, :numeroIdentificacion, :fechaNacimiento, :direccionResidencia, :telefono, :empresaAfiliado, :tipoParentesco, :idContribuyente)", nativeQuery = true)
    void insertarAfiliado(@Param("idPaciente") long idPaciente, 
                          @Param("nombre") String nombre, 
                          @Param("tipoIdentificacion") String tipoIdentificacion, 
                          @Param("numeroIdentificacion") long numeroIdentificacion, 
                          @Param("fechaNacimiento") String fechaNacimiento, 
                          @Param("direccionResidencia") String direccionResidencia, 
                          @Param("telefono") long telefono, 
                          @Param("empresaAfiliado") String empresaAfiliado, 
                          @Param("tipoParentesco") String tipoParentesco, 
                          @Param("idContribuyente") Long idContribuyente);

    @Modifying
    @Transactional
    @Query(value = "UPDATE AFILIADO SET nombre = :nombre, tipoIdentificacion = :tipoIdentificacion, numeroIdentificacion = :numeroIdentificacion, fechaNacimiento = :fechaNacimiento, direccionResidencia = :direccionResidencia, telefono = :telefono, empresaAfiliado = :empresaAfiliado, tipoParentesco = :tipoParentesco, AFILIADO_idPaciente = :idContribuyente WHERE idPaciente = :idPaciente", nativeQuery = true)
    void actualizarAfiliado(@Param("idPaciente") long idPaciente, 
                            @Param("nombre") String nombre, 
                            @Param("tipoIdentificacion") String tipoIdentificacion, 
                            @Param("numeroIdentificacion") long numeroIdentificacion, 
                            @Param("fechaNacimiento") String fechaNacimiento, 
                            @Param("direccionResidencia") String direccionResidencia, 
                            @Param("telefono") long telefono, 
                            @Param("empresaAfiliado") String empresaAfiliado, 
                            @Param("tipoParentesco") String tipoParentesco, 
                            @Param("idContribuyente") Long idContribuyente);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM AFILIADO WHERE idPaciente = :idPaciente", nativeQuery = true)
    void eliminarAfiliado(@Param("idPaciente") long idPaciente);
}