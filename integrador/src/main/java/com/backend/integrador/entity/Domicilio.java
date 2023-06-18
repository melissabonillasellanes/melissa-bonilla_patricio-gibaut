package com.backend.integrador.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "domicilios")
public class Domicilio {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String calle;

    @Column
    private int numero;

    @Column
    private String ciudad;

    @Column
    private String departamento;

    @Column
    private String pais;

    @Column
    private String codigoPostal;

    public Domicilio(Long id, String calle, int numero, String ciudad, String departamento, String pais, String codigoPostal) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.pais = pais;
        this.codigoPostal = codigoPostal;
    }

    public Domicilio(String calle, int numero, String ciudad, String departamento, String pais, String codigoPostal) {
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.pais = pais;
        this.codigoPostal = codigoPostal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
