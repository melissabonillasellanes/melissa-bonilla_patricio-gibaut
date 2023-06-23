package com.backend.integrador.service;

import com.backend.integrador.dto.TurnoDto;
import com.backend.integrador.entity.Turno;
import com.backend.integrador.exception.BadRequestException;
import com.backend.integrador.exception.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {

    TurnoDto nuevoTurno(TurnoDto turno) throws BadRequestException, ResourceNotFoundException;

    List<TurnoDto> listarTurnos();

    TurnoDto buscarTurnoPorId(Long id) throws ResourceNotFoundException;

    TurnoDto actualizarTurno(Turno turno) throws ResourceNotFoundException;

    void eliminarTurno(Long id) throws ResourceNotFoundException;
}
