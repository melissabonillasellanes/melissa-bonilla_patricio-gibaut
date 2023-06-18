package com.backend.integrador.entity;

import jakarta.persistence.*;



@Entity
@Table(name = "ODONTOLOGOS")
public class Odontologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(15)")
    private String numeroMatricula;

    @Column(columnDefinition = "VARCHAR(50)")
    private String nombre;

    @Column(columnDefinition = "VARCHAR(50)")
    private String apellido;

    public Odontologo(){};

    public Odontologo(String numeroMatricula, String nombre, String apellido) {
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }


    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
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
}