package com.backend.integrador.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "DOMICILIOS")
public class Domicilio {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String calle;

    private int numero;

    private String ciudad;

    private String departamento;

    private String pais;

    private String codigoPostal;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    public Domicilio(){};

    public Domicilio(String calle, int numero, String ciudad, String departamento, String pais, String codigoPostal, Paciente paciente) {
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.pais = pais;
        this.codigoPostal = codigoPostal;
        this.paciente = paciente;
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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
