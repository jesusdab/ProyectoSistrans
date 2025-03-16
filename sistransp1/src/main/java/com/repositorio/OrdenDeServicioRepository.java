package com.repositorio;

import java.sql.Date;
import java.util.Collection;

import com.modelo.Afiliado;
import com.modelo.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.modelo.OrdenDeServicio;

public interface OrdenDeServicioRepository extends JpaRepository<OrdenDeServicio, Long> {

    @Query(value = "SELECT * FROM ORDEN_DE_SERVICIO", nativeQuery = true)
    Collection<OrdenDeServicio> obtenerTodasLasOrdenes();

    @Query(value = "SELECT * FROM ORDEN_DE_SERVICIO WHERE idOrden = :idOrden", nativeQuery = true)
    OrdenDeServicio obtenerOrdenPorId(@Param("idOrden") Long idOrden);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ORDEN_DE_SERVICIO (idOrden, fecha, servicioPrescrito, estado, MEDICO_numeroRegistroMedico, AFILIADO_idPaciente) VALUES (:idOrden, TO_DATE(:fecha, 'YYYY-MM-DD'), :servicioPrescrito, :estado, :numeroRegistroMedico, :idPaciente)", nativeQuery = true)
    void insertarOrden(@Param("idOrden") Long idOrden,
                       @Param("fecha") Date fecha,
                       @Param("servicioPrescrito") String servicioPrescrito, 
                       @Param("estado") String estado, 
                       @Param("Medico") Medico medico,
                       @Param("Afiliado") Afiliado afiliado);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ORDEN_DE_SERVICIO SET fecha = TO_DATE(:fecha, 'YYYY-MM-DD'), servicioPrescrito = :servicioPrescrito, estado = :estado, MEDICO_numeroRegistroMedico = :numeroRegistroMedico, AFILIADO_idPaciente = :idPaciente WHERE idOrden = :idOrden", nativeQuery = true)
    void actualizarOrden(@Param("idOrden") Long idOrden,
                         @Param("fecha") Date fecha,
                         @Param("servicioPrescrito") String servicioPrescrito, 
                         @Param("estado") String estado,
                         @Param("Medico") Medico medico,
                         @Param("Afiliado") Afiliado afiliado);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ORDEN_DE_SERVICIO WHERE idOrden = :idOrden", nativeQuery = true)
    void eliminarOrden(@Param("idOrden") Long idOrden);
}