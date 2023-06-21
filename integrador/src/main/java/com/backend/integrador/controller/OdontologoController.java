package com.backend.integrador.controller;

import com.backend.integrador.dto.OdontologoDto;
import com.backend.integrador.dto.PacienteDto;
import com.backend.integrador.entity.Odontologo;
import com.backend.integrador.entity.Paciente;
import com.backend.integrador.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarOdontologo(@RequestBody Odontologo odontologo){
        OdontologoDto odontologoDS = odontologoService.agregarOdontologo(odontologo);
        return new ResponseEntity<>(odontologoDS, null, HttpStatus.CREATED);
    }

    @GetMapping
    private List<OdontologoDto> listarOdontologos(){ return odontologoService.listarTodos();}


    @GetMapping("/abm")
    @CrossOrigin(origins = "http://localhost:*")
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView("odontologo");
        modelAndView.addObject("nombre", "Usuario");
        return modelAndView;
    }


}
