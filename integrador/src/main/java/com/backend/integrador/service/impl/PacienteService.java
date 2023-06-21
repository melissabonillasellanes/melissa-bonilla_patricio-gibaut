package com.backend.integrador.service.impl;

import com.backend.integrador.dto.DomicilioDto;
import com.backend.integrador.dto.PacienteDto;
import com.backend.integrador.entity.Domicilio;
import com.backend.integrador.entity.Paciente;
import com.backend.integrador.repository.PacienteRepository;
import com.backend.integrador.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    private final PacienteRepository pacienteRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, ObjectMapper objectMapper) {
        this.pacienteRepository = pacienteRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    public PacienteDto agregarPaciente(Paciente paciente) {

        Paciente pacienteNvo = pacienteRepository.save(paciente);
        PacienteDto pacienteDto = objectMapper.convertValue(pacienteNvo, PacienteDto.class);

        LOGGER.info("Paciente {} agregado.", pacienteDto);
        return pacienteDto;
    }

    @Override
    public List<PacienteDto> listarTodos() {

        List<Paciente> pacientes = pacienteRepository.findAll();
        List<PacienteDto> pacienteDtos = pacientes.stream()
                .map(paciente -> {
                    Domicilio dom = paciente.getDomicilio();
                    DomicilioDto domicilioDto = objectMapper.convertValue(dom, DomicilioDto.class);
                    return new PacienteDto(paciente.getId(),paciente.getNombre(), paciente.getApellido(), paciente.getDni(), paciente.getFechaAlta(), domicilioDto);
                })
                .toList();

        LOGGER.info("Listado de pacientes {} ", pacienteDtos);
        return pacienteDtos;
    }

    @Override
    public PacienteDto buscarPorId(Long id) {
        return null;
    }

    @Override
    public PacienteDto eliminarPaciente(Long id) {
        return null;
    }
}
