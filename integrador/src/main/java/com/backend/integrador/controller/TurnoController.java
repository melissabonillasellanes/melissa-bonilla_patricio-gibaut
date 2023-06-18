package com.backend.integrador.controller;

import com.backend.integrador.dto.TurnoDto;
import com.backend.integrador.entity.Turno;
import com.backend.integrador.service.impl.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private TurnoService turnoService;

    @Autowired
    private TurnoController (TurnoService turnoService){
        this.turnoService = turnoService;
    }


    @GetMapping()
    private List<TurnoDto> listarTurnos(){
        return turnoService.listarTodos();
    }



}
