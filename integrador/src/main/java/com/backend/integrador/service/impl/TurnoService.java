package com.backend.integrador.service.impl;

import com.backend.integrador.dao.IDao;
import com.backend.integrador.entity.Turno;
import com.backend.integrador.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnoService implements ITurnoService {
    private IDao<Turno> turnoIDao;
    private ObjectMapper objectMapper;

    @Autowired
    public TurnoService(IDao<Turno> turnoIDao, ObjectMapper objectMapper) {
        this.turnoIDao = turnoIDao;
        this.objectMapper = objectMapper;
    }

    @Override
    public TurnoDto guardarTurno(Turno turno) {
        return TurnoDto.fromTurno(turnoIDao.agregar(turno));
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
    public TurnoDto buscarTurnoPorId(int id) {
        return TurnoDto.fromTurno(turnoIDao.listarPorId(id));
    }

    @Override
    public TurnoDto actualizarTurno(Turno turno) {
        return TurnoDto.fromTurno(turnoIDao.actualizar(turno));
    }

    @Override
    public void eliminarTurno(int id) {
        turnoIDao.eliminar(id);
    }
}