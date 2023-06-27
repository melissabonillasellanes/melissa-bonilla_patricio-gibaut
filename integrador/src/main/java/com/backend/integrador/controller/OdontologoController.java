package com.backend.integrador.controller;

import com.backend.integrador.dto.OdontologoDto;
import com.backend.integrador.dto.PacienteDto;
import com.backend.integrador.entity.Odontologo;
import com.backend.integrador.entity.Paciente;
import com.backend.integrador.exception.BadRequestException;
import com.backend.integrador.exception.ResourceNotFoundException;
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
    public ResponseEntity<OdontologoDto> agregarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        OdontologoDto odontologoDS = odontologoService.agregarOdontologo(odontologo);
        return new ResponseEntity<>(odontologoDS, null, HttpStatus.CREATED);
    }

    /*  PREGUNTAR POR QUÉ FUNCIONA IGUAL CON EL ?
        @PostMapping("/agregar")
    public ResponseEntity<?> agregarOdontologo(@RequestBody Odontologo odontologo){
        OdontologoDto odontologoDS = odontologoService.agregarOdontologo(odontologo);
        return new ResponseEntity<>(odontologoDS, null, HttpStatus.CREATED);
    }
     */


    @GetMapping
    private List<OdontologoDto> listarOdontologos() throws BadRequestException { return odontologoService.listarTodos();}


    @GetMapping("/{id}")
    public ResponseEntity<?> buscarOdontologoPorId(@PathVariable Long id) throws ResourceNotFoundException {

        return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), null, HttpStatus.OK);
    }


    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoDto> actualizarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        return new ResponseEntity<>(odontologoService.actualizarOdontologo(odontologo), null, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("Odontologo eliminado");
    }

    // ABM desde odontologo.html
    // @GetMapping("/abm")
    // @CrossOrigin(origins = "http://localhost:*")
    // public ModelAndView welcome() {
        // ModelAndView modelAndView = new ModelAndView("odontologo");
        // modelAndView.addObject("nombre", "Usuario");
  //      return modelAndView;
//    }


}
