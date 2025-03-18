package com.modelo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "AFILIADO")
public class Afiliado {

    @Id
    private Long idPaciente;

    private String nombre;

    private String tipoIdentificacion;

    private Long numeroIdentificacion;

    private Date fechaNacimiento;

    private String direccionResidencia;

    private Long telefono;

    private String empresaAfiliado;

    private String tipoParentesco;

    private Long idContribuyente;

    

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public Long getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(Long numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getEmpresaAfiliado() {
        return empresaAfiliado;
    }

    public void setEmpresaAfiliado(String empresaAfiliado) {
        this.empresaAfiliado = empresaAfiliado;
    }

    public String getTipoParentesco() {
        return tipoParentesco;
    }

    public void setTipoParentesco(String tipoParentesco) {
        this.tipoParentesco = tipoParentesco;
    }

    public Long getIdContribuyente() {
        return idContribuyente;
    }

    public void setIdContribuyente(Long idContribuyente) {
        this.idContribuyente = idContribuyente;
    }
}