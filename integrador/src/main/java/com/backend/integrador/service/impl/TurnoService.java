package com.backend.integrador.service.impl;

import com.backend.integrador.dto.DomicilioDto;
import com.backend.integrador.dto.OdontologoDto;
import com.backend.integrador.dto.PacienteDto;
import com.backend.integrador.dto.TurnoDto;
import com.backend.integrador.entity.Odontologo;
import com.backend.integrador.entity.Paciente;
import com.backend.integrador.entity.Turno;
import com.backend.integrador.repository.TurnoRepository;
import com.backend.integrador.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, ObjectMapper objectMapper) {
        this.turnoRepository = turnoRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public TurnoDto guardarTurno(Turno turno) {
        Turno turnoNuevo = turnoRepository.save(turno);
        TurnoDto turnoDto = new TurnoDto();

        //TurnoDto turnoDto = objectMapper.convertValue(turnoNuevo, TurnoDto.class );

        PacienteDto pacienteDtoNuevo = objectMapper.convertValue(turnoNuevo.getPaciente(), PacienteDto.class);
        OdontologoDto odontologoDtoNuevo = objectMapper.convertValue(turnoNuevo.getOdontologo(), OdontologoDto.class);
        turnoDto.setOdontologo(odontologoDtoNuevo);
        turnoDto.setPaciente(pacienteDtoNuevo);
        turnoDto.setFecha(turnoNuevo.getFechaTurno());

        return  turnoDto;
    }

    @Override
    public List<TurnoDto> listarTodos() {

        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoDto> turnoDtos = turnos.stream()
                .map(turno -> {
                    Odontologo odontologo = turno.getOdontologo();
                    OdontologoDto odontologoDto = objectMapper.convertValue(odontologo, OdontologoDto.class);
                    Paciente paciente = turno.getPaciente();
                    PacienteDto pacienteDto = objectMapper.convertValue(paciente, PacienteDto.class);

                    return new TurnoDto(pacienteDto, odontologoDto, turno.getFechaTurno());
        })
                .toList();
        LOGGER.info("Lista de todos los turnos: {}", turnoDtos);

        return turnoDtos;

    }

    @Override
    public TurnoDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoDto turnoDto = null;

        if (turnoBuscado != null) {
            turnoDto = objectMapper.convertValue(turnoBuscado, TurnoDto.class);
            LOGGER.info("Turno encontrado: {}", turnoDto);

        } else LOGGER.info("El id no se encuentra en la base de datos");

        return turnoDto;
    }

    @Override
    public TurnoDto actualizarTurno(Turno turno) {
        Turno turnoAActualizar = turnoRepository.findById(turno.getId()).orElse(null);
        TurnoDto turnoActualizadoDto = null;

        if (turnoAActualizar != null){
            turnoAActualizar = turno;
            turnoRepository.save(turnoAActualizar);

            turnoActualizadoDto = objectMapper.convertValue(turnoAActualizar, TurnoDto.class);
            LOGGER.info("Turno actualizado con éxito: {}", turnoActualizadoDto);
        }

        return turnoActualizadoDto;
    }

    @Override
    public void eliminarTurno(Long id) {
      turnoRepository.deleteById(id);
      LOGGER.info("Turno eliminado con id: {}", id);

    }
}