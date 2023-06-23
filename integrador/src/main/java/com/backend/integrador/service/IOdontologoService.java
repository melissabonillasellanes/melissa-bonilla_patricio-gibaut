package com.backend.integrador.service;

import com.backend.integrador.dto.OdontologoDto;
import com.backend.integrador.entity.Odontologo;
import com.backend.integrador.exception.BadRequestException;
import com.backend.integrador.exception.ResourceNotFoundException;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

public interface IOdontologoService {

    OdontologoDto agregarOdontologo(Odontologo odontologo) throws BadRequestException;

    List<OdontologoDto> listarTodos();

    OdontologoDto buscarOdontologoPorId(Long id) throws ResourceNotFoundException;

    OdontologoDto actualizarOdontologo(Odontologo odontologo) throws BadRequestException, ResourceNotFoundException;

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;

}
