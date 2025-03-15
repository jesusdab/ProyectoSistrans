package com.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.modelo.ServicioDeSalud;

public interface ServicioDeSaludRepository extends JpaRepository<ServicioDeSalud, Long> {

    @Query(value = "SELECT * FROM SERVICIO_DE_SALUD", nativeQuery = true)
    Collection<ServicioDeSalud> obtenerTodosLosServicios();

    @Query(value = "SELECT * FROM SERVICIO_DE_SALUD WHERE idServicio = :idServicio", nativeQuery = true)
    ServicioDeSalud obtenerServicioPorId(@Param("idServicio") long idServicio);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO SERVICIO_DE_SALUD (idServicio, tipoServicio) VALUES (:idServicio, :tipoServicio)", nativeQuery = true)
    void insertarServicio(@Param("idServicio") long idServicio, 
                          @Param("tipoServicio") String tipoServicio);

    @Modifying
    @Transactional
    @Query(value = "UPDATE SERVICIO_DE_SALUD SET tipoServicio = :tipoServicio WHERE idServicio = :idServicio", nativeQuery = true)
    void actualizarServicio(@Param("idServicio") long idServicio, 
                            @Param("tipoServicio") String tipoServicio);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM SERVICIO_DE_SALUD WHERE idServicio = :idServicio", nativeQuery = true)
    void eliminarServicio(@Param("idServicio") long idServicio);
}