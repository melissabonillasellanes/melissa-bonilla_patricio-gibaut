package com.backend.integrador.service;

import com.backend.integrador.dto.TurnoDto;
import com.backend.integrador.entity.Turno;

import java.util.List;

public interface ITurnoService {
    TurnoDto guardarTurno(Turno turno);

    List<TurnoDto> listarTodos();

    TurnoDto buscarTurnoPorId(Long id);

    TurnoDto actualizarTurno(Turno turno);

    void eliminarTurno(Long id);
}