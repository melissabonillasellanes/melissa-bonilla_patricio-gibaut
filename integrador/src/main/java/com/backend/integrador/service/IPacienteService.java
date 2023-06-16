package com.backend.integrador.service;

import com.backend.integrador.dto.PacienteDto;
import com.backend.integrador.entity.Paciente;

import java.util.List;

public interface IPacienteService {
    List<PacienteDto> listarPacientes();

    PacienteDto buscarPacientePorDni(String dni);
    PacienteDto buscarPacientePorId(Long id);

    PacienteDto agregarPaciente(Paciente paciente);
    PacienteDto actualizarPaciente(Paciente paciente);
    void eliminarPaciente(Long id);
}
