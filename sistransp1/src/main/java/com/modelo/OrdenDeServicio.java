package com.modelo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ORDEN_DE_SERVICIO")
public class OrdenDeServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOrden;

    private Date fecha;

    private String servicioPrescrito;

    private String estado;

    @ManyToOne
    private Medico medico;

    @ManyToOne
    private Afiliado afiliado;

    public OrdenDeServicio() 
    {;}

    public OrdenDeServicio(Long idOrden, Date fecha, String servicioPrescrito, String estado, Medico medico, Afiliado afiliado) {
        this.idOrden = idOrden;
        this.fecha = fecha;
        this.servicioPrescrito = servicioPrescrito;
        this.estado = estado;
        this.medico = medico;
        this.afiliado = afiliado;
    }

    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getServicioPrescrito() {
        return servicioPrescrito;
    }

    public void setServicioPrescrito(String servicioPrescrito) {
        this.servicioPrescrito = servicioPrescrito;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Afiliado getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(Afiliado afiliado) {
        this.afiliado = afiliado;
    }
}