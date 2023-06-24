package com.backend.integrador.service.impl;

import com.backend.integrador.dto.DomicilioDto;
import com.backend.integrador.dto.PacienteDto;
import com.backend.integrador.entity.Domicilio;
import com.backend.integrador.entity.Paciente;
import com.backend.integrador.exception.BadRequestException;
import com.backend.integrador.exception.ResourceNotFoundException;
import com.backend.integrador.utils.JsonPrinter;
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
    public PacienteDto agregarPaciente(Paciente paciente) throws BadRequestException {
        Paciente pacienteNvo = null;
        PacienteDto pacienteDto = null;
        DomicilioDto domicilioDto = null;
        if (paciente != null && paciente.getDomicilio() != null) {
            domicilioDto = objectMapper.convertValue(paciente.getDomicilio(), DomicilioDto.class);
            pacienteNvo = pacienteRepository.save(paciente);
            pacienteDto = objectMapper.convertValue(pacienteNvo, PacienteDto.class);
            pacienteDto.setDomicilioDto(domicilioDto);
        } else {
            LOGGER.error("No se pudo agregar el paciente");
            throw new BadRequestException("No se pudo agregar el paciente");
        }
        LOGGER.info("PS-Paciente {} agregado.", pacienteDto);
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

        LOGGER.info("PS-Listado de pacientes {} ", pacienteDtos);
        return pacienteDtos;
    }

    @Override
    public PacienteDto buscarPorId(Long id) throws ResourceNotFoundException {

        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
        PacienteDto pacienteDto = null;
        if (pacienteBuscado != null) {
            DomicilioDto domicilioDto = objectMapper.convertValue(pacienteBuscado.getDomicilio(), DomicilioDto.class);
            pacienteDto = objectMapper.convertValue(pacienteBuscado, PacienteDto.class);
            pacienteDto.setDomicilioDto(domicilioDto);

            LOGGER.info("PS-Paciente encontrado: {}", JsonPrinter.toString(pacienteDto));

        } else {
            LOGGER.error("PS-El id de Paciente no se encuentra registrado en la base de datos");
            throw new ResourceNotFoundException("PS-El id de Paciente no se encuentra registrado en la base de datos");
        }
        return pacienteDto;
    }

    @Override
    public PacienteDto actualizarPaciente(Paciente paciente) throws ResourceNotFoundException {
        Paciente pacienteAActualizar = pacienteRepository.findById(paciente.getId()).orElse(null);
        PacienteDto pacienteActualizadoDto = null;

        if (pacienteAActualizar != null) {
            pacienteAActualizar = paciente;
            pacienteRepository.save(pacienteAActualizar);

            DomicilioDto domicilioDto = objectMapper.convertValue(pacienteAActualizar.getDomicilio(), DomicilioDto.class);
            pacienteActualizadoDto = objectMapper.convertValue(pacienteAActualizar, PacienteDto.class);
            pacienteActualizadoDto.setDomicilioDto(domicilioDto);
            LOGGER.info("Paciente actualizado con exito: {}", JsonPrinter.toString(pacienteActualizadoDto));

        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el paciente no se encuentra registrado");
            throw new ResourceNotFoundException("No fue posible actualizar los datos ya que el paciente no se encuentra registrado");
        }

        return pacienteActualizadoDto;

    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if(buscarPorId(id) != null){
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el paciente con id {}", id);
        } else {
            LOGGER.error("No se ha encontrado el paciente con id " + id);
            throw new ResourceNotFoundException("No se ha encontrado el paciente con id " + id);

        }
    }
}