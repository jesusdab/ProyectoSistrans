package com.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ips_registradas")
public class IPS {

    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String direccionIPS;
    private String ubicacion;
    private String tipo;

    public IPS(String direccionIPS, String ubicacion, String tipo) {
        this.direccionIPS = direccionIPS;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
    }

    public IPS() 
    {;}
    
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccionIP() {
        return direccionIPS;
    }

    public void setDireccionIP(String direccionIPS) {
        this.direccionIPS = direccionIPS;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipoConexion(String tipo) {
        this.tipo = tipo;
    }
}
