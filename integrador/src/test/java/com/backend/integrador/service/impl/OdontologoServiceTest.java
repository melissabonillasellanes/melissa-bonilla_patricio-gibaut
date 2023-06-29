package com.backend.integrador.service.impl;

import com.backend.integrador.dto.OdontologoDto;
import com.backend.integrador.entity.Odontologo;
import com.backend.integrador.exception.BadRequestException;
import com.backend.integrador.exception.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;


    @Test
    @Order(1)
    void deberíaAgregarOdontologo() throws BadRequestException {

        Odontologo odontologoAAgregar = new Odontologo("Juan", "Gallego", "UY-12345678");
        OdontologoDto odontologoDto = odontologoService.agregarOdontologo(odontologoAAgregar);

        Assertions.assertNotNull(odontologoDto);
    }

    @Test
    @Order(2)
    void cuandoNoSeCumplaElFormatoDeMatricula_noDeberiaInsertarElOdontologo() {
        Odontologo odontologo = new Odontologo("3433333", "patricia", "Lopez");
        Assertions.assertThrows(ConstraintViolationException.class, () -> odontologoService.agregarOdontologo(odontologo));
    }

    @Test
    void listarTodos() {
    }

    @Test
    @Order(4)
    void deberiaEliminarElOdontologoId1() throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo((long) 1);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
    }
}