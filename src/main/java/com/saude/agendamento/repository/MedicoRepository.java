package com.saude.agendamento.repository;

import com.saude.agendamento.model.Especialidade;
import com.saude.agendamento.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Medico findById(long id);
    Medico findByCrm(String crm);
    List<Medico> findByEspecialidadeIs(Especialidade especialidade);
}
