package com.backend.integrador.service.impl;

import com.backend.integrador.dto.OdontologoDto;
import com.backend.integrador.entity.Odontologo;
import com.backend.integrador.exception.BadRequestException;
import com.backend.integrador.exception.ResourceNotFoundException;
import com.backend.integrador.repository.OdontologoRepository;
import com.backend.integrador.service.IOdontologoService;
import com.backend.integrador.utils.JsonPrinter;
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
    public OdontologoDto agregarOdontologo(Odontologo odontologo) throws BadRequestException {
        Odontologo odontologoNvo = null;
        OdontologoDto odontologoDto = null;
        if (odontologo != null) {
            odontologoNvo = odontologoRepository.save(odontologo);
            odontologoDto = objectMapper.convertValue(odontologoNvo, OdontologoDto.class);
        } else {
            LOGGER.error("El odontologo ingresado no es correcto");
            throw new BadRequestException("El odontologo ingresado no es correcto");
        }
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
    public OdontologoDto buscarOdontologoPorId(Long id) throws ResourceNotFoundException {

        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoDto odontologoDto = null;

        if(odontologoBuscado != null) {
            odontologoDto = objectMapper.convertValue(odontologoBuscado, OdontologoDto.class);
            LOGGER.info("OS-Odontólogo encontrado: {}", JsonPrinter.toString(odontologoDto));
        } else {
            LOGGER.info("OS-El id de Odontólogo no se encuentra registrado en la base de datos");
            throw new ResourceNotFoundException("No se ha encontrado el odontologo en la base de datos con id: " + id);
        }
        return odontologoDto;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if (buscarOdontologoPorId(id) != null) {
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el odontologo con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el odontologo con id " + id);
            //throw new ResourceNotFoundException("No se ha encontrado el odontologo con id " + id);
            throw new ResourceNotFoundException("No se ha encontrado el odontologo con id: " + id);
        }
    }

    public OdontologoDto actualizarOdontologo(Odontologo odontologo) throws BadRequestException {

        Odontologo odontologoAActualizar = odontologoRepository.findById(odontologo.getId()).orElse(null);
        OdontologoDto odontologoDtoActualizado = null;
        if (odontologoAActualizar != null) {
            odontologoAActualizar = odontologo;
            odontologoRepository.save(odontologoAActualizar);
            odontologoDtoActualizado = objectMapper.convertValue(odontologoAActualizar, OdontologoDto.class);
            LOGGER.warn("Odontologo actualizado: {}", JsonPrinter.toString(odontologoDtoActualizado));
        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el odontologo no se encuentra registrado");
            throw new BadRequestException("No fue posible actualizar los datos ya que el odontologo no se encuentra registrado");
        }
        return odontologoDtoActualizado;
    }
}
