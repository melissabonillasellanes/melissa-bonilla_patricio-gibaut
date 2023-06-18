package com.backend.integrador.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Table(name = "turnos")
public class Turno {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private Paciente paciente;

    @Column
    private Odontologo odontologo;

    @Column
    private LocalDateTime fechaTurno;

    public Turno(Long id, Paciente paciente, Odontologo odontologo, LocalDateTime fechaTurno) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaTurno = fechaTurno;
    }

    public Turno(Paciente paciente, Odontologo odontologo, LocalDateTime fechaTurno) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaTurno = fechaTurno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDateTime getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(LocalDateTime fechaTurno) {
        this.fechaTurno = fechaTurno;
    }
}
