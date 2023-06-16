package com.backend.integrador.dao.impl;

import com.backend.integrador.repository.H2Connection;
import com.backend.integrador.repository.IDao;
import com.backend.integrador.entity.Odontologo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoDaoH2.class);

    @Override
    public List<Odontologo> listarTodos() {
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            connection = H2Connection.getConnection();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Odontologo odontologo = new Odontologo(rs.getLong(1), rs.getInt(2), rs.getString(3), rs.getString(4));
                odontologos.add(odontologo);
            }
            LOGGER.info("Listado de odontologos:");
            for (Odontologo odontologo : odontologos) {
                LOGGER.info(odontologo.getNombre() + " " + odontologo.getApellido());
            }


        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la base de datos " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return odontologos;
    }

    @Override
    public Odontologo agregar(Odontologo odontologo) {
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement("INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, odontologo.getNumeroMatricula());
            ps.setString(2, odontologo.getNombre());
            ps.setString(3, odontologo.getApellido());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                odontologo.setId(rs.getLong(1));
            }

            connection.commit();
            LOGGER.info("Se guardo el odontologo: " + odontologo.getNombre() + " " + odontologo.getApellido());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Error");
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la base de datos " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return odontologo;
    }

    @Override
    public void modificar(Odontologo odontologo) {

    }

    @Override
    public void eliminar(int id) {

    }

    @Override
    public Odontologo listarPorId(int id) {
        return null;
    }

    @Override
    public Odontologo buscarPorCriterio(String dni) {
        return null;
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        return null;
    }
}
