package com.repositorio;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.modelo.IPS;

import jakarta.transaction.Transactional;

public interface IpsRepository extends JpaRepository<IPS,Long>{

    @Query(value="SELECT * FROM IPS",nativeQuery=true)
    Collection<IPS> darIPSs();

    @Query(value="SELECT * FROM IPS WHERE nit= nit",nativeQuery=true)
    IPS darIPS(@Param("nit") Long nit);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO IPS (nit, tipo, nombre, direccion, telefono, serviciosPrestados) VALUES (:nit, :tipo, :nombre, :direccion, :telefono, :serviciosPrestados)", nativeQuery = true)
    void insertarIPS(@Param("nit") Long nit,
                     @Param("tipo") String tipo, 
                     @Param("nombre") String nombre, 
                     @Param("direccion") String direccion, 
                     @Param("telefono") Long telefono,
                     @Param("serviciosPrestados") List<String> serviciosPrestados);

    @Modifying
    @Transactional
    @Query(value = "UPDATE IPS SET tipo = :tipo, nombre = :nombre, direccion = :direccion, telefono = :telefono, serviciosPrestados = :serviciosPrestados WHERE nit = :nit", nativeQuery = true)
    void actualizarIPS(@Param("nit") Long nit,
                       @Param("tipo") String tipo, 
                       @Param("nombre") String nombre, 
                       @Param("direccion") String direccion, 
                       @Param("telefono") Long telefono,
                       @Param("serviciosPrestados") List<String> serviciosPrestados);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM IPS WHERE nit = :nit", nativeQuery = true)
    void eliminarIPS(@Param("nit") Long nit);
    

    
}
