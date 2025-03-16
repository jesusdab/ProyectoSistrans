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
    private Integer nit;

    private String nombre;
    private String direccionIPS;
    private Integer telefono ;
    private String tipo;
    private List<String> serviciosPrestados;

    public IPS(String nombre, String direccionIPS, Integer telefono, String tipo, List<String> serviciosPrestados) {
        this.nombre = nombre;
        this.direccionIPS = direccionIPS;
        this.telefono = telefono;
        this.tipo = tipo;
        this.serviciosPrestados = serviciosPrestados;
    }

    public IPS() 
    {;}
    
    
    // Getters y Setters
    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
        this.nit = nit;
    }

    public String getDireccionIPS() {
        return direccionIPS;
    }

    public void setDireccionIPS(String direccionIPS) {
        this.direccionIPS = direccionIPS;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
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

    public List<String> getServiciosPrestados() {
        return serviciosPrestados;
    }

    public void setServiciosPrestados(List<String> serviciosPrestados) {
        this.serviciosPrestados = serviciosPrestados;
    }
}
