package com.backend.integrador.service;

import com.backend.integrador.dto.TurnoDto;
import com.backend.integrador.entity.Turno;

import java.util.List;

public interface ITurnoService {

    TurnoDto nuevoTurno(TurnoDto turno);

    List<TurnoDto> listarTurnos();

    TurnoDto buscarTurnoPorId(Long id);

    TurnoDto actualizarTurno(Turno turno);

    void eliminarTurno(Long id);
}
