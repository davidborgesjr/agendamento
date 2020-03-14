package com.saude.agendamento.repository;

import com.saude.agendamento.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findByEmailEquals(String email);
    Paciente findById(long id);
}
