package com.saude.agendamento.repository;

import com.saude.agendamento.model.Atendimento;
import com.saude.agendamento.model.Medico;
import com.saude.agendamento.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    List<Atendimento> findByMedico(Medico medico);
    List<Atendimento> findByPaciente(Paciente paciente);
    Atendimento findById(long id);
}
