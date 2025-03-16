package com.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SERVICIO_DE_SALUD")
public class ServicioDeSalud {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idServicio;
    
    private String tipoServicio;

    public ServicioDeSalud() 
    {;}

    public ServicioDeSalud(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }
}