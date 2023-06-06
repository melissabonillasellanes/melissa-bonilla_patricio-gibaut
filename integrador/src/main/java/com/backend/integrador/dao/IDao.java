package com.backend.integrador.dao;

import java.util.List;

public interface IDao <T> {

    List<T> listarTodos();

    T agregar(T t);

    void modificar(T t);

    void eliminar(int id);

    List<T> listarPorId(int id);

    T buscarPorCriterio(String dni);

    T actualizar(T odontologo);
}