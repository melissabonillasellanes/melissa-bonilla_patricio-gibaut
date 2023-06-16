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
    public OdontologoService(OdontologoRepository odontologoRepository, ObjectMapper objectMapper){
        this.odontologoRepository = odontologoRepository;
        this.objectMapper = objectMapper;
    };



    public OdontologoDto buscarOdontologoPorId(Long id) {

        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoDto odontologoDto = null;

        if(odontologoBuscado != null){
            odontologoDto = objectMapper.convertValue(odontologoBuscado, OdontologoDto.class);
            LOGGER.info("Odontólogo encontrado: {}", odontologoDto);

        } else LOGGER.info("El id: {} no se encuentra en la base de datos", id);

        return odontologoDto;
    }

    public List<OdontologoDto> listarOdontologos() {

        List<Odontologo> odontologos = odontologoRepository.findAll();
        List<OdontologoDto> odontologoDtos = odontologos.stream()
                .map(odontologo -> {
                    return new OdontologoDto(odontologo.getApellido(),odontologo.getNombre(),odontologo.getNumeroMatricula());
                })
                .toList();


        return odontologoDtos;
    }

    @Override
    public OdontologoDto agregarOdontologo(Odontologo odontologo) {

        Odontologo odontologoNuevo = odontologoRepository.save(odontologo);
        OdontologoDto odontologoDtoNvo = objectMapper.convertValue(odontologoNuevo, OdontologoDto.class);

        LOGGER.info("Nuevo odontólogo ingresado con éxito: {}", odontologoDtoNvo);

        return odontologoDtoNvo;
    }

    @Override
    public OdontologoDto actualizarOdontologo(Odontologo odontologo) {
        Odontologo odontologoAActualizar = odontologoRepository.findById(odontologo.getId()).orElse(null);
        OdontologoDto odontologoActualizado = null;

        if(odontologoAActualizar != null) {
            odontologoAActualizar = odontologo;
            odontologoRepository.save(odontologoAActualizar);

            odontologoActualizado = objectMapper.convertValue(odontologoAActualizar, OdontologoDto.class);

            LOGGER.info("Odontólogo con id {} actualizado", odontologo.getId());

        }else LOGGER.error("No fue posible actualizar los datos, odontólogo no encontrado");

        return odontologoActualizado;

    }

    public void eliminarOdontologo(Long id) {
        odontologoRepository.deleteById(id);
    }

}