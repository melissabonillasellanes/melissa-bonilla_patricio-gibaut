package com.backend.integrador;

import com.backend.integrador.dto.OdontologoDto;
import com.backend.integrador.entity.Odontologo;
import com.backend.integrador.exception.BadRequestException;
import com.backend.integrador.exception.ResourceNotFoundException;
import com.backend.integrador.service.impl.OdontologoService;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontolgoServiceTest {

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
    @Order(3)
    void deberiaListarUnSoloOdontologo() {
        List<OdontologoDto> odontologosDtos = odontologoService.listarTodos();
        Assertions.assertEquals(0, odontologosDtos.size());
    }

    @Test
    @Order(4)
    void deberiaEliminarElOdontologoId1() throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo((long) 1);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
    }


    @Test
    @Order(5)
    void deberiaRetornarUnaListaVacia() throws ResourceNotFoundException {
        List<OdontologoDto> odontologosDtos = odontologoService.listarTodos();
        Assertions.assertTrue(odontologosDtos.isEmpty());


    }
}
