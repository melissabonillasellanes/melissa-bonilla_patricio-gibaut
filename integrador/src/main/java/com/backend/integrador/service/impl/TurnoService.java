package com.backend.integrador.service.impl;

import com.backend.integrador.dto.OdontologoDto;
import com.backend.integrador.dto.PacienteDto;
import com.backend.integrador.dto.TurnoDto;
import com.backend.integrador.entity.Odontologo;
import com.backend.integrador.entity.Paciente;
import com.backend.integrador.entity.Turno;
import com.backend.integrador.repository.TurnoRepository;
import com.backend.integrador.service.ITurnoService;
import com.backend.integrador.utils.JsonPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    private final TurnoRepository turnoRepository;

    // ********** Para qué  necesita estos metodos de los otros services?
    private final PacienteService pacienteService;

    private final OdontologoService odontologoService;
    // **********

    private final ObjectMapper objectMapper;



    @Autowired
    public TurnoService(TurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService, ObjectMapper objectMapper) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
        this.objectMapper = objectMapper;
    }



    // La siguiente versión permite ingresar sólo el Id de Paciente y Odontólogo al generar un nuevo turno.

    @Override
    public TurnoDto nuevoTurno(TurnoDto turnoIn) {

        TurnoDto turnoDto = null;

        // con los parametros (Id en formato String) de paciente y odontólogo busco y asigno los objetos.
        Paciente paciente = objectMapper.convertValue(pacienteService.buscarPorId(Long.valueOf(turnoIn.getPaciente())), Paciente.class);
        Odontologo odontologo = objectMapper.convertValue(odontologoService.buscarOdontologoPorId(Long.valueOf(turnoIn.getOdontologo())), Odontologo.class);
        LOGGER.info("Paciente: {}",paciente);
        LOGGER.info("Odontologo: {}", odontologo);

        if(paciente == null || odontologo == null) {
            if(paciente == null && odontologo == null) {
                LOGGER.error("El paciente y el odontologo no se encuentran en nuestra base de datos");
                //throw new BadRequestException("El paciente no se encuentra en nuestra base de datos");
            }
            else if (paciente == null){
                LOGGER.error("El paciente no se encuentra en nuestra base de datos");
                //throw new BadRequestException("El paciente no se encuentra en nuestra base de datos");
            } else {
                LOGGER.error("El odontologo no se encuentra en nuestra base de datos");
                //throw new BadRequestException("El odontologo no se encuentra en nuestra base de datos");
            }

        } else {
            Turno turno = new Turno(paciente, odontologo, turnoIn.getFechaTurno());
            Turno turnoNvo = turnoRepository.save(turno);
            turnoDto = new TurnoDto(turnoNvo.getId(),String.valueOf(paciente.getId()),String.valueOf(odontologo.getId()),turnoNvo.getFechaTurno());
            LOGGER.info("Nuevo turno registrado con exito: {}", JsonPrinter.toString(turnoDto));
        }

        return turnoDto;

    }

    /*   Versión Inicial, funcionaba pero había que enviar en el body los objetos completos de odontologo y paciente, y no retornaba bien esos objetos, faltaba el fromTurno seguramente.
    @Override
    public TurnoDto nuevoTurno(Turno turno) {
        Turno turnoNvo = turnoRepository.save(turno);
        TurnoDto turnoDto = objectMapper.convertValue(turnoNvo, TurnoDto.class);
        LOGGER.info("Turno agendado! {}", turnoDto);
        return turnoDto;
    }
     */

    /*   ********* VERSION HECHA EN CLASE *** Todavía requiere ingresar los objetos enteros paciente y odontólogo para dar de alta nuevo turno.

        @Override
    public TurnoDto guardarTurno(Turno turno) throws BadRequestException {
        TurnoDto turnoDto = null;
        PacienteDto paciente = pacienteService.buscarPacientePorId(turno.getPaciente().getId());
        OdontologoDto odontologo = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());

        if(paciente == null || odontologo == null) {
            if(paciente == null && odontologo == null) {
                LOGGER.error("El paciente y el odontologo no se encuentran en nuestra base de datos");
                throw new BadRequestException("El paciente no se encuentra en nuestra base de datos");
            }
            else if (paciente == null){
                LOGGER.error("El paciente no se encuentra en nuestra base de datos");
                throw new BadRequestException("El paciente no se encuentra en nuestra base de datos");
            } else {
                LOGGER.error("El odontologo no se encuentra en nuestra base de datos");
                throw new BadRequestException("El odontologo no se encuentra en nuestra base de datos");
            }

        } else {
            turnoDto = TurnoDto.fromTurno(turnoRepository.save(turno));
            LOGGER.info("Nuevo turno registrado con exito: {}", JsonPrinter.toString(turnoDto));
        }

        return turnoDto;
    }

     */

    @Override
    public List<TurnoDto> listarTurnos() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoDto> turnoDtoList = turnos.stream()
                .map(TurnoDto::fromTurno)
                .toList();

        LOGGER.info("TS-Lista de todos los turnos: {}", JsonPrinter.toString(turnoDtoList));
        return turnoDtoList;
    }

    @Override
    public TurnoDto buscarTurnoPorId(Long id) {



        return null;
    }

    @Override
    public TurnoDto actualizarTurno(Turno turno) {


        return null;
    }

    @Override
    public void eliminarTurno(Long id) {

    }
}
