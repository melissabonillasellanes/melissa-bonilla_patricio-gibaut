package com.backend.integrador.dto;

import com.backend.integrador.entity.Turno;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDto {

    private PacienteDto pacienteDto;
    private OdontologoDto odontologoDto;
    private LocalDateTime fecha;

    public TurnoDto() {
    }

    public TurnoDto(PacienteDto paciente, OdontologoDto odontologo, LocalDateTime fecha) {
        this.pacienteDto = paciente;
        this.odontologoDto = odontologo;
        this.fecha = fecha;
    }

    public PacienteDto getPaciente() {
        return pacienteDto;
    }

    public void setPaciente(PacienteDto paciente) {
        this.pacienteDto = paciente;
    }

    public OdontologoDto getOdontologo() {
        return odontologoDto;
    }

    public void setOdontologo(OdontologoDto odontologo) {
        this.odontologoDto = odontologo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /*  ESTO ERA DE DAO ??

    public static TurnoDto fromTurno(Turno turno) {
        String paciente = turno.getPaciente().getNombre() + " " + turno.getPaciente().getApellido();
        String odontologo = turno.getOdontologo().getNombre() + " " + turno.getOdontologo().getApellido();
        //return new TurnoDto(paciente, odontologo, turno.getFechaTurno());
        return null;
    }
    */
}