package com.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicios")
public class IpsServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Servicio_de_salud_Idservicio")
    private Long idServicio;

    @Column(name = "ips_nit", nullable = false)
    private Long nitIps;

    
    public IpsServicio() {;}

   
    public IpsServicio(Long idServicio, Long nitIps) {
        this.idServicio = idServicio;
        this.nitIps = nitIps;
    }

    
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
