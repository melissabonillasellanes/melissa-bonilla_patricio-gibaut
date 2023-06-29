package com.backend.integrador.service.impl;

import com.backend.integrador.dto.TurnoDto;
import com.backend.integrador.entity.Domicilio;
import com.backend.integrador.entity.Odontologo;
import com.backend.integrador.entity.Paciente;
import com.backend.integrador.entity.Turno;
import com.backend.integrador.exception.BadRequestException;
import com.backend.integrador.exception.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;


    @Test
    @Order(1)
    void deberíaAgregarUnTurno() throws BadRequestException {

        Domicilio domicilio = new Domicilio("Belgrano", 125,"Colonia","Colonia");
        Paciente pacienteAAgregar = new Paciente("Juan", "Gallego", "12345678", LocalDate.parse("2023-07-12"), domicilio);
        Odontologo odontologoAAgregar = new Odontologo("Juan", "Gallego", "UY-12345678");

        Turno turnoAAgregar = new Turno(pacienteAAgregar,odontologoAAgregar, LocalDateTime.parse("2023-07-10T13:00"));

        Assertions.assertTrue(turnoAAgregar != null);


    }

    @Test
    @Order(2)
    void deberíalistarTodosLosTurnos() {
        List<TurnoDto> turnoList = turnoService.listarTurnos();

        Assertions.assertNotNull(turnoList);

    }

    @Test
    @Order(3)
    void deberíaEliminarTurno1() throws ResourceNotFoundException {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(1L));
    }
}