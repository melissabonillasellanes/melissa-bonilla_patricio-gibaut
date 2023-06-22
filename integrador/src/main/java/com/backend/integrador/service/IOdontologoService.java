package com.backend.integrador.service;

import com.backend.integrador.dto.OdontologoDto;
import com.backend.integrador.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {

    OdontologoDto agregarOdontologo(Odontologo odontologo);

    List<OdontologoDto> listarTodos();

    OdontologoDto buscarOdontologoPorId(Long id);

    OdontologoDto actualizarOdontologo(Odontologo odontologo);

    void eliminarOdontologo(Long id);

}
