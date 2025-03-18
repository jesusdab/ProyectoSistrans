package com.repositorio;

import java.util.Collection;
import java.util.Date;

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
    Afiliado obtenerAfiliadoPorId(@Param("idPaciente") Long idPaciente);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO AFILIADO (idPaciente, nombre, tipoIdentificacion, numeroIdentificacion, fechaNacimiento, direccionResidencia, telefono, empresaAfiliado, tipoParentesco, afiliado_idPaciente) VALUES (:idPaciente, :nombre, :tipoIdentificacion, :numeroIdentificacion, :fechaNacimiento, :direccionResidencia, :telefono, :empresaAfiliado, :tipoParentesco, :afiliado_idPaciente)", nativeQuery = true)
    void insertarAfiliado(@Param("idPaciente") Long idPaciente, 
                          @Param("nombre") String nombre, 
                          @Param("tipoIdentificacion") String tipoIdentificacion, 
                          @Param("numeroIdentificacion") Long numeroIdentificacion, 
                          @Param("fechaNacimiento") Date fechaNacimiento, 
                          @Param("direccionResidencia") String direccionResidencia, 
                          @Param("telefono") Long telefono, 
                          @Param("empresaAfiliado") String empresaAfiliado, 
                          @Param("tipoParentesco") String tipoParentesco, 
                          @Param("afiliado_idPaciente") Long afiliado_idPaciente);

    @Modifying
    @Transactional
    @Query(value = "UPDATE AFILIADO SET nombre = :nombre, tipoIdentificacion = :tipoIdentificacion, numeroIdentificacion = :numeroIdentificacion, fechaNacimiento = :fechaNacimiento, direccionResidencia = :direccionResidencia, telefono = :telefono, empresaAfiliado = :empresaAfiliado, tipoParentesco = :tipoParentesco, afiliado_idPaciente = :afiliado_idPaciente WHERE idPaciente = :idPaciente", nativeQuery = true)
    void actualizarAfiliado(@Param("idPaciente") Long idPaciente, 
                            @Param("nombre") String nombre, 
                            @Param("tipoIdentificacion") String tipoIdentificacion, 
                            @Param("numeroIdentificacion") Long numeroIdentificacion, 
                            @Param("fechaNacimiento") Date fechaNacimiento, 
                            @Param("direccionResidencia") String direccionResidencia, 
                            @Param("telefono") Long telefono, 
                            @Param("empresaAfiliado") String empresaAfiliado, 
                            @Param("tipoParentesco") String tipoParentesco, 
                            @Param("afiliado_idPaciente") Long afiliado_idPaciente);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM AFILIADO WHERE idPaciente = :idPaciente", nativeQuery = true)
    void eliminarAfiliado(@Param("idPaciente") Long idPaciente);
}