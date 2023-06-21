package com.backend.integrador.controller;

import com.backend.integrador.dto.PacienteDto;
import com.backend.integrador.dto.TurnoDto;
import com.backend.integrador.entity.Paciente;
import com.backend.integrador.entity.Turno;
import com.backend.integrador.service.impl.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private TurnoService turnoService;

    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevoTurno(@RequestBody Turno turno){
        TurnoDto turnoDS = turnoService.nuevoTurno(turno);
        return new ResponseEntity<>(turnoDS, null, HttpStatus.CREATED);
    }

    @GetMapping
    private List<TurnoDto> listarTurnos(){ return turnoService.listarTurnos();}


}
