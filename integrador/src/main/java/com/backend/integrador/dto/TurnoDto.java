package com.backend.integrador.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDto {

    private Long id;
    private PacienteDto pacienteDto;
    private OdontologoDto odontologoDto;
    private LocalDateTime fechaTurno;
    private String dni;
    private String matricula;

    public TurnoDto(){}


    public TurnoDto(Long id, PacienteDto pacienteDto, OdontologoDto odontologoDto, LocalDateTime fechaTurno) {
        this.id = id;
        this.pacienteDto = pacienteDto;
        this.odontologoDto = odontologoDto;
        this.fechaTurno = fechaTurno;
    }

    public TurnoDto(PacienteDto pacienteDto, OdontologoDto odontologoDto, LocalDateTime fechaTurno) {
        this.pacienteDto = pacienteDto;
        this.odontologoDto = odontologoDto;
        this.fechaTurno = fechaTurno;
    }

    public Long getId() {
        return id;
    }

    public PacienteDto getPacienteDto() {
        return pacienteDto;
    }

    public void setPacienteDto(PacienteDto pacienteDto) {
        this.pacienteDto = pacienteDto;
    }

    public OdontologoDto getOdontologoDto() {
        return odontologoDto;
    }

    public void setOdontologoDto(OdontologoDto odontologoDto) {
        this.odontologoDto = odontologoDto;
    }

    public LocalDateTime getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(LocalDateTime fechaTurno) {
        this.fechaTurno = fechaTurno;
    }
}
