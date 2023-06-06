package com.backend.integrador.service.impl;

import com.backend.integrador.dao.IDao;
import com.backend.integrador.entity.Paciente;
import com.backend.integrador.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private IDao<Paciente> pacienteIDao;

    @Autowired
    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente agregarPaciente(Paciente paciente) {
        return pacienteIDao.agregar(paciente);
    }

    @Override
    public Paciente actualizarPaciente(Paciente paciente) {
        return pacienteIDao.actualizar(paciente);
    }

    public Paciente buscarPacientePorId(int id) {
        return (Paciente) pacienteIDao.listarPorId(id);
    }

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteIDao.listarTodos();
    }

    @Override
    public Paciente buscarPacientePorDni(String dni) {
        return pacienteIDao.buscarPorCriterio(dni);
    }

    public void eliminarPaciente(int id) {
        pacienteIDao.eliminar(id);
    }

}