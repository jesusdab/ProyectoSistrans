package com.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "servicios")
public class IpsServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Long idServicio;

    @Column(name = "nit_ips", nullable = false)
    private Long nitIps;

    // Constructor vacío (requerido por JPA)
    public IpsServicio() {}

    // Constructor con parámetros
    public IpsServicio(Long idServicio, Long nitIps) {
        this.idServicio = idServicio;
        this.nitIps = nitIps;
    }

    // Getters y Setters
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
