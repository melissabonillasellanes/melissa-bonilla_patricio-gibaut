package com.backend.integrador.service.impl;

import com.backend.integrador.dto.OdontologoDto;
import com.backend.integrador.dto.PacienteDto;
import com.backend.integrador.dto.TurnoDto;
import com.backend.integrador.entity.Domicilio;
import com.backend.integrador.entity.Odontologo;
import com.backend.integrador.entity.Paciente;
import com.backend.integrador.entity.Turno;
import com.backend.integrador.exception.BadRequestException;
import com.backend.integrador.exception.ResourceNotFoundException;
import com.backend.integrador.repository.OdontologoRepository;
import com.backend.integrador.repository.PacienteRepository;
import com.backend.integrador.repository.TurnoRepository;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnoServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);


    @Autowired
    private TurnoService turnoService;

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private OdontologoRepository odontologoRepository;


    @Test
    @Order(1)
    void deberíaAgregarUnTurno() throws BadRequestException, ResourceNotFoundException {


        Domicilio domicilio = new Domicilio("Belgrano", 125,"Colonia","Colonia");
        Paciente pacienteAAgregar = new Paciente("Juan", "Gallego", "12345678", LocalDate.parse("2023-07-12"), domicilio);
        PacienteDto pacienteNvo = pacienteService.agregarPaciente(pacienteAAgregar);

        Odontologo odontologoAAgregar = new Odontologo("Juan", "Gallego", "UY-12345678");
        OdontologoDto odontologoNvo = odontologoService.agregarOdontologo(odontologoAAgregar);

        //Turno turno = new Turno(pacienteAAgregar, odontologoAAgregar, LocalDateTime.parse("2023-07-10T13:00"));
        //Turno turnoNvo = turnoRepository.save(turno);
        //TurnoDto turnoDto = new TurnoDto(turnoNvo.getId(),"1","1",turnoNvo.getFechaTurno());

        TurnoDto turnoDto = new TurnoDto(1L,"1","1",LocalDateTime.parse("2023-07-10T13:00"));

        TurnoDto turnoAAgregar = turnoService.nuevoTurno(turnoDto);

        Assertions.assertTrue(turnoAAgregar != null);

    }


    /*    **** TEST USANDO REPOSITORIES EN VEZ DE SERVICES **** OK ****
    void deberíaAgregarUnTurno() throws BadRequestException, ResourceNotFoundException {

        Domicilio domicilio = new Domicilio("Belgrano", 125,"Colonia","Colonia");
        Paciente pacienteAAgregar = new Paciente("Juan", "Gallego", "12345678", LocalDate.parse("2023-07-12"), domicilio);
        Paciente pacienteNvo = pacienteRepository.save(pacienteAAgregar);

        Odontologo odontologoAAgregar = new Odontologo("Juan", "Gallego", "UY-12345678");
        Odontologo odontologoNvo = odontologoRepository.save(odontologoAAgregar);

        Turno turno = new Turno(pacienteAAgregar, odontologoAAgregar, LocalDateTime.parse("2023-07-10T13:00"));
        Turno turnoNvo = turnoRepository.save(turno);
        TurnoDto turnoDto = new TurnoDto(turnoNvo.getId(),"1","1",turnoNvo.getFechaTurno());

        TurnoDto turnoAAgregar = turnoService.nuevoTurno(turnoDto);

        Assertions.assertTrue(turnoAAgregar != null);
    }

     */

    @Test
    @Order(2)
    void deberíalistarTodosLosTurnos() {
        List<TurnoDto> turnoList = turnoService.listarTurnos();

        Assertions.assertNotNull(turnoList);
        Assertions.assertEquals(1,turnoList.size());
        LOGGER.warn(turnoList.toString());

    }

    @Test
    @Order(3)
    void deberíaEliminarTurno1() throws ResourceNotFoundException {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(2L));
    }
}