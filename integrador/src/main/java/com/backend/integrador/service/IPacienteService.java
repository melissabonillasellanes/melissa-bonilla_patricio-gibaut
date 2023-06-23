package com.backend.integrador.service;

import com.backend.integrador.dto.PacienteDto;
import com.backend.integrador.entity.Paciente;
import com.backend.integrador.exception.BadRequestException;
import com.backend.integrador.exception.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {

    PacienteDto agregarPaciente(Paciente paciente) throws BadRequestException;
    List<PacienteDto> listarTodos();

    PacienteDto buscarPorId(Long id) throws ResourceNotFoundException;

    PacienteDto actualizarPaciente(Paciente paciente) throws ResourceNotFoundException;

    void eliminarPaciente(Long id) throws ResourceNotFoundException;

}
