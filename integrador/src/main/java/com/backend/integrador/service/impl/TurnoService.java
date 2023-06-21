package com.backend.integrador.service.impl;

import com.backend.integrador.dto.TurnoDto;
import com.backend.integrador.entity.Odontologo;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    private final TurnoRepository turnoRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, ObjectMapper objectMapper) {
        this.turnoRepository = turnoRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    public TurnoDto nuevoTurno(Turno turno) {
        Turno turnoNvo = turnoRepository.save(turno);
        TurnoDto turnoDto = objectMapper.convertValue(turnoNvo, TurnoDto.class);
        LOGGER.info("Turno agendado! {}", turnoDto);
        return turnoDto;
    }

    @Override
    public List<TurnoDto> listarTurnos() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoDto> turnoDtos = turnos.stream()
                .map(turno -> {
                    TurnoDto turnoDto = objectMapper.convertValue(turno, TurnoDto.class);
                    //return new TurnoDto(turnoDto.getId(),turnoDto.getPacienteDto().getDni(), turnoDto.getOdontologoDto().getMatricula(), turnoDto.getFechaTurno());
                    // la opción anterior nos permitiría ?? traer los datos que pueda devolver el listado (DNI para el paciente y MATRICULA para el Odontólogo), pero el constructor no coincide.
                    // El constructor de TurnoDto espera un objeto PacienteDto (no un String) y un OdontologoDto (y no otro String).
                    return new TurnoDto(turnoDto.getId(),turnoDto.getPacienteDto(), turnoDto.getOdontologoDto(), turnoDto.getFechaTurno());
                })
                .toList();

        LOGGER.info("Listado de Turnos {}",turnoDtos);
        return turnoDtos;
    }

    @Override
    public TurnoDto buscarTurnoPorId(Long id) {
        return null;
    }

    @Override
    public TurnoDto eliminarTurno(Long id) {
        return null;
    }
}
