package com.backend.service;

import com.backend.dao.IDao;
import com.backend.entity.Odontologo;

import java.util.List;

public class OdontologoService {
    public IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoIDao.guardar(odontologo);
    }

    public List<Odontologo> listarOdonotologo() {
        return odontologoIDao.listarTodos();
    }
}
