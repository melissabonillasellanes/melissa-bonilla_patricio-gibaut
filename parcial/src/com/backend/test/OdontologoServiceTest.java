package com.backend.test;

import com.backend.dao.impl.OdontologoDaoEnMemoria;
import com.backend.dao.impl.OdontologoDaoH2;
import com.backend.entity.Odontologo;
import com.backend.service.OdontologoService;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {
    private static Connection connection = null;
    private OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    private OdontologoService odontologoServiceMem = new OdontologoService(new OdontologoDaoEnMemoria(new ArrayList<>()));

    @Test
    public void deberiaAgregarOdontologoEnMemoria() {
        Odontologo odontologoTest = new Odontologo(345672, "Alicia", "GonzalezMemoria");

        Odontologo odontologoResultado = odontologoServiceMem.guardarOdontologo(odontologoTest);

        assertNotNull(odontologoResultado);
        assertEquals(345672, odontologoResultado.getNumeroMatricula());
    }

    @Test
    public void deberiaAgregarOdontologoEnMemoria2() {
        Odontologo odontologoTest = new Odontologo(987654, "Juan", "Memoria");

        Odontologo odontologoResultado = odontologoServiceMem.guardarOdontologo(odontologoTest);

        assertNotNull(odontologoResultado);
        assertEquals(987654, odontologoResultado.getNumeroMatricula());
    }

    @Test
    public void deberiaAgregarOdontologoEnMemoria3() {
        Odontologo odontologoTest = new Odontologo(345888, "Marta", "PerezMemoria");

        Odontologo odontologoResultado = odontologoServiceMem.guardarOdontologo(odontologoTest);

        assertNotNull(odontologoResultado);
        assertEquals(345888, odontologoResultado.getNumeroMatricula());
    }

    @Test
    public void deberiaAgregarUnOdontologo() {
        Odontologo odontologoTest = new Odontologo(34567, "Alicia", "Gonzalez");

        Odontologo odontologoResultado = odontologoService.guardarOdontologo(odontologoTest);

        assertNotNull(odontologoResultado);
        assertEquals(34567, odontologoResultado.getNumeroMatricula());
    }

    @Test
    public void deberiaAgregarUnOdontologo1() {
        Odontologo odontologoTest = new Odontologo(986567, "José", "Perez");

        Odontologo odontologoResultado = odontologoService.guardarOdontologo(odontologoTest);

        assertNotNull(odontologoResultado);
        assertEquals(986567, odontologoResultado.getNumeroMatricula());
    }

    @Test
    public void deberiaAgregarUnOdontologo2() {
        Odontologo odontologoTest = new Odontologo(765844, "Maria", "Rodriguez");

        Odontologo odontologoResultado = odontologoService.guardarOdontologo(odontologoTest);

        assertNotNull(odontologoResultado);
        assertEquals(765844, odontologoResultado.getNumeroMatricula());
    }

    @Test
    public void deberiaListarTodosLosOdontologosMemoria() {
        List<Odontologo> odontologosTest2 = odontologoServiceMem.listarOdonotologo();
        assertFalse(odontologosTest2.isEmpty());
        //assertTrue(odontologosTest2.size() >= 3);
    }

    @Test
    public void deberiaListarTodosLosOdontologos() {
        List<Odontologo> odontologosTest = odontologoService.listarOdonotologo();
        assertFalse(odontologosTest.isEmpty());
        assertTrue(odontologosTest.size() >= 3);
    }

}