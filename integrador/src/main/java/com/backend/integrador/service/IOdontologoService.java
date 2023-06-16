package com.backend.integrador.service;

import com.backend.integrador.dto.OdontologoDto;
import com.backend.integrador.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {
        OdontologoDto buscarOdontologoPorId(Long id);
        List<OdontologoDto> listarOdontologos();
        OdontologoDto agregarOdontologo(Odontologo odontologo);
        OdontologoDto actualizarOdontologo(Odontologo odontologo);
        void eliminarOdontologo(Long id);
}

