package com.backend.test;

import com.backend.dao.impl.OdontologoDaoH2;
import com.backend.entity.Odontologo;
import com.backend.service.OdontologoService;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {
    private static Connection connection = null;
    private OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    @Test
    public void deberiaAgregarUnOdontologo() {
        Odontologo odontologoTest = new Odontologo(34567, "Alicia", "Gonzalez");

        Odontologo odontologoResultado = odontologoService.guardarOdontologo(odontologoTest);

        assertNotNull(odontologoResultado);
        assertEquals(34567, odontologoResultado.getNumeroMatricula());
    }


}