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
    @Query(value = "INSERT INTO Servicio_ofrecido (Servicio_de_salud_Idservicio, ips_nit) VALUES (:Servicio_de_salud_Idservicio, :ips_nit)", nativeQuery = true)
    void asignarServicio(@Param("Servicio_de_salud_Idservicio") long nit, @Param("ips_nit") long idServicio);

    
}
