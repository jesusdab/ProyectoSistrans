package com.modelo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CITA")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCita;

    private Date fecha;

    private Date hora;

    private Long ipsNit;

    private Long idOrdenServicio;

    private Long numeroRegistroMedico;

    private Long idPacienteAfiliado;

    public Cita() 
    {;}

    public Cita(Long idCita, Date fecha, Date hora, Long ipsNit, Long idOrdenServicio, Long numeroRegistroMedico, Long idPacienteAfiliado) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.ipsNit = ipsNit;
        this.idOrdenServicio = idOrdenServicio;
        this.numeroRegistroMedico = numeroRegistroMedico;
        this.idPacienteAfiliado = idPacienteAfiliado;
    }

    public Long getIdCita() {
        return idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Long getIpsNit() {
        return ipsNit;
    }

    public void setIpsNit(Long ipsNit) {
        this.ipsNit = ipsNit;
    }

    public Long getIdOrdenServicio() {
        return idOrdenServicio;
    }

    public void setIdOrdenServicio(Long idOrdenServicio) {
        this.idOrdenServicio = idOrdenServicio;
    }

    public Long getNumeroRegistroMedico() {
        return numeroRegistroMedico;
    }

    public void setNumeroRegistroMedico(Long numeroRegistroMedico) {
        this.numeroRegistroMedico = numeroRegistroMedico;
    }

    public Long getIdPacienteAfiliado() {
        return idPacienteAfiliado;
    }

    public void setIdPacienteAfiliado(Long idPacienteAfiliado) {
        this.idPacienteAfiliado = idPacienteAfiliado;
    }
}