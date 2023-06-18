package com.backend.integrador.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;


@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private Domicilio domicilio;

    @Column
    private String dni;

    @Column
    private LocalDate fechaAlta;

    public Paciente(Long id, String nombre, String apellido, Domicilio domicilio, String dni, LocalDate fechaAlta) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.dni = dni;
        this.fechaAlta = fechaAlta;
    }

    public Paciente(String nombre, String apellido, Domicilio domicilio, String dni, LocalDate fechaAlta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
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

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
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
