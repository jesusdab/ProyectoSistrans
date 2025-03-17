package com.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.modelo.IpsServicio;

@Repository
public interface IpsServicioRepository extends JpaRepository<IpsServicio, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO IPS_SERVICIO (nit, idServicio) VALUES (:nit, :idServicio)", nativeQuery = true)
    void asignarServicio(@Param("nit") long nit, @Param("idServicio") long idServicio);

    
}
