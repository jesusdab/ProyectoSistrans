package com.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicios")
public class IpsServicio {

    private Long idServicio;

    private Long nitIps;


    public IpsServicio()
    {;}


    public Long getIdServicio() {
        return idServicio;
    }


    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }


    public Long getNitIps() {
        return nitIps;
    }


    public void setNitIps(Long nitIps) {
        this.nitIps = nitIps;
    }

    



    
}
