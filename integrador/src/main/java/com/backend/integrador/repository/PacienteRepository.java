package com.backend.integrador.repository;

import com.backend.integrador.entity.Paciente;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}