package com.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "ips_registradas")
public class IPS {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long nit;

    private String nombre;
    private String direccionIPS;
    private Long telefono ;
    private String tipo;

    public IPS(String nombre, String direccionIPS, Long telefono, String tipo) {
        this.nombre = nombre;
        this.direccionIPS = direccionIPS;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    public IPS() 
    {;}
    
    
    // Getters y Setters
    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }

    public String getDireccionIPS() {
        return direccionIPS;
    }

    public void setDireccionIPS(String direccionIPS) {
        this.direccionIPS = direccionIPS;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}