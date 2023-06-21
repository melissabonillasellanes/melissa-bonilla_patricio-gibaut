package com.backend.integrador.service;

import com.backend.integrador.dto.PacienteDto;
import com.backend.integrador.entity.Paciente;

import java.util.List;

public interface IPacienteService {

    PacienteDto agregarPaciente(Paciente paciente);
    List<PacienteDto> listarTodos();

    PacienteDto buscarPorId(Long id);

    PacienteDto eliminarPaciente(Long id);

}
