package com.backend.integrador;

import com.backend.integrador.dao.impl.OdontologoDaoH2;
import com.backend.integrador.entity.Odontologo;
import com.backend.integrador.service.OdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	public void deberiaListarTodosLosOdontologos() {
		List<Odontologo> odontologosTest = odontologoService.listarOdonotologo();

		assertFalse(odontologosTest.isEmpty());
		assertTrue(odontologosTest.size() >= 3);
	}

}
