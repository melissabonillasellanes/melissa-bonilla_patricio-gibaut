package com.backend.integrador.dto;

import com.backend.integrador.entity.Domicilio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDto {
    private Long id;
    private String nombre;
    private String apellido;
    private DomicilioDto domicilioDto;
    private String dni;
    private LocalDate fechaAlta;

    public PacienteDto() {
    }

    public PacienteDto(Long id, String nombre, String apellido, DomicilioDto domicilioDto, String dni, LocalDate fechaAlta) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilioDto = domicilioDto;
        this.dni = dni;
        this.fechaAlta = fechaAlta;
    }

    public PacienteDto(String nombre, String apellido, DomicilioDto domicilioDto, String dni, LocalDate fechaAlta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilioDto = domicilioDto;
        this.dni = dni;
        this.fechaAlta = fechaAlta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public DomicilioDto getDomicilioDto() {
        return domicilioDto;
    }

    public void setDomicilioDto(DomicilioDto domicilioDto) {
        this.domicilioDto = domicilioDto;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}
