package com.backend.integrador.service.impl;

import com.backend.integrador.dto.TurnoDto;
import com.backend.integrador.entity.Turno;
import com.backend.integrador.repository.TurnoRepository;
import com.backend.integrador.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, ObjectMapper objectMapper) {
        this.turnoRepository = turnoRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public TurnoDto guardarTurno(Turno turno) {
        Turno turnoNuevo = turnoRepository.save(turno);
        TurnoDto turnoDto = objectMapper.convertValue(turno)
    }

    @Override
    public List<TurnoDto> listarTodos() {
        //List<Turno> turnoList = turnoIDao.listarTodos();
        //List<TurnoDto> turnoDtoList = new ArrayList<>();
        //for (Turno t : turnoList){
        //turnoDtoList.add(TurnoDto.fromTurno(t));
        //}
        //return turnoDtoList;
        //con lambda (funcion anonima)
        //return turnoList.stream().map(t -> TurnoDto.fromTurno(t)).toList();
        //con referencia al metodo
        return turnoIDao.listarTodos().stream().map(TurnoDto::fromTurno).toList();
    }

    @Override
    public TurnoDto buscarTurnoPorId(Long id) {
        return TurnoDto.fromTurno(turnoIDao.listarPorId(id));
    }

    @Override
    public TurnoDto actualizarTurno(Turno turno) {
        return TurnoDto.fromTurno(turnoIDao.actualizar(turno));
    }

    @Override
    public void eliminarTurno(Long id) {
        turnoIDao.eliminar(id);
    }
}