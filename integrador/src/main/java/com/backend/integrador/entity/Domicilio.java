package com.backend.integrador.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "DOMICILIOS")
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La calle no puede ser nula")
    @NotBlank(message = "Debe especificarse la calle")
    private String calle;
    private Integer numero;

    @NotNull(message = "La Ciudad no puede ser nula")
    @NotBlank(message = "Debe especificarse la ciudad")
    private String ciudad;
    private String departamento;


    public Domicilio() {}


    public Domicilio(String calle, Integer numero, String ciudad, String departamento) {
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.departamento = departamento;

    }

    public Long getId() {
        return id;
    }
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
