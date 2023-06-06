package com.backend.integrador.service;

import com.backend.integrador.dao.IDao;
import com.backend.integrador.entity.Odontologo;

import java.util.List;

public class OdontologoService {
    public IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoIDao.agregar(odontologo);
    }

    public List<Odontologo> listarOdonotologo() {
        return odontologoIDao.listarTodos();
    }
}
