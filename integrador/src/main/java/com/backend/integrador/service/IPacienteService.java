package com.backend.integrador.service;

import com.backend.integrador.entity.Paciente;

import java.util.List;

public interface IPacienteService {
    List<Paciente> listarPacientes();

    Paciente buscarPacientePorDni(String dni);
    Paciente buscarPacientePorId(int id);

    Paciente agregarPaciente(Paciente paciente);
    Paciente actualizarPaciente(Paciente paciente);
    void eliminarPaciente(int id);
}
