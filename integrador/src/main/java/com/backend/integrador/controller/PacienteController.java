package com.backend.integrador.controller;

import com.backend.integrador.dto.PacienteDto;
import com.backend.integrador.entity.Paciente;
import com.backend.integrador.service.impl.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService){
        this.pacienteService = pacienteService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarPaciente(@RequestBody Paciente paciente){
        PacienteDto pacienteDS = pacienteService.agregarPaciente(paciente);
        return new ResponseEntity<>(pacienteDS, null, HttpStatus.CREATED);
    }

    @GetMapping()
    private List<PacienteDto> listarPacientes(){
        return pacienteService.listarTodos();
    }
}
