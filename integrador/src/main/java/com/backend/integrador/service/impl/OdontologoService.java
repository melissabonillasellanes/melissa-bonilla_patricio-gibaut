package com.backend.integrador.service.impl;

import com.backend.integrador.dto.OdontologoDto;
import com.backend.integrador.entity.Odontologo;
import com.backend.integrador.repository.OdontologoRepository;
import com.backend.integrador.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    private final OdontologoRepository odontologoRepository;
    private final ObjectMapper objectMapper;


    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository, ObjectMapper objectMapper) {
        this.odontologoRepository = odontologoRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    public OdontologoDto agregarOdontologo(Odontologo odontologo) {

        Odontologo odontologoNvo = odontologoRepository.save(odontologo);
        OdontologoDto odontologoDto = objectMapper.convertValue(odontologoNvo, OdontologoDto.class);

        return odontologoDto;
    }

    @Override
    public List<OdontologoDto> listarTodos() {

        List<Odontologo> odontologos = odontologoRepository.findAll();
        List<OdontologoDto> odontologoDtos = odontologos.stream()
                .map(odontologo -> {
                    return new OdontologoDto(odontologo.getId(), odontologo.getNombre(), odontologo.getApellido(), odontologo.getMatricula());
                })
                .toList();

        return odontologoDtos;
    }

    @Override
    public OdontologoDto buscarOdontologoPorId(Long id) {
        return null;
    }

    @Override
    public OdontologoDto eliminarOdontologo(Long id) {
        return null;
    }
}
