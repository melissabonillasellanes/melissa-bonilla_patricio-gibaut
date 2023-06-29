package com.backend.integrador.service.impl;

import com.backend.integrador.dto.OdontologoDto;
import com.backend.integrador.dto.PacienteDto;
import com.backend.integrador.entity.Domicilio;
import com.backend.integrador.entity.Odontologo;
import com.backend.integrador.entity.Paciente;
import com.backend.integrador.exception.BadRequestException;
import com.backend.integrador.exception.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void listarTodos() {
        List<PacienteDto> pacienteDtoList = pacienteService.listarTodos();

        Assertions.assertEquals(0, pacienteDtoList.size());
    }

    @Test
    @Order(2)
    void buscarPorId() throws ResourceNotFoundException {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> pacienteService.buscarPorId(1L));

    }

    @Test
    @Order(3)
    void deberíaAgregarPaciente() throws BadRequestException {
        Domicilio domicilio = new Domicilio("Belgrano", 125,"Colonia","Colonia");
        Paciente pacienteAAgregar = new Paciente("Juan", "Gallego", "12345678", LocalDate.parse("2023-07-12"), domicilio);
        PacienteDto pacienteDto = pacienteService.agregarPaciente(pacienteAAgregar);

        Assertions.assertNotNull(pacienteDto);
    }

}