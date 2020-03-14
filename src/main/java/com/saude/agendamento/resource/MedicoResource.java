package com.saude.agendamento.resource;

import com.saude.agendamento.model.Atendimento;
import com.saude.agendamento.model.Especialidade;
import com.saude.agendamento.model.Medico;
import com.saude.agendamento.payloads.AgendamentoMedico;
import com.saude.agendamento.repository.AtendimentoRepository;
import com.saude.agendamento.repository.EspecialidadeRepository;
import com.saude.agendamento.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/medicos")
public class MedicoResource {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private EspecialidadeRepository especialidadeRepository;
    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @GetMapping(value="")
    public List<Medico> getMedicos(){
        return medicoRepository.findAll();
    }

    @GetMapping(value="/{id}")
    public Medico getMedico(@PathVariable(value="id") long id){
        return  medicoRepository.findById(id);
    }

    @GetMapping(value="/especialidades")
    public List<Especialidade> getEspecialidades(){
        return especialidadeRepository.findAll();
    }

    @GetMapping(value="/especialidades/{id}")
    public List<Medico> getMedicosByEspecialidade(@PathVariable(value = "id") long id){
        Especialidade especialidade = new Especialidade();
        especialidade.setCodigo(id);
        return medicoRepository.findByEspecialidadeIs(especialidade);
    }

    @PostMapping(value="/cadastro")
    public Medico setMedico(@RequestBody Medico medico){
        return medicoRepository.save(medico);
    }

    @PostMapping(value="/atendimento")
    public Atendimento setAtendimento(@RequestBody AgendamentoMedico agendamentoMedico){
        String[] data = agendamentoMedico.getData().split("/");

        Calendar calendario = Calendar.getInstance();
        calendario.set(Integer.valueOf(data[2]), Integer.valueOf(data[1]), Integer.valueOf(data[0]));

        Date dataAtendimento = calendario.getTime();

        Atendimento atendimento = new Atendimento();
        atendimento.setData(dataAtendimento);
        Medico medico = medicoRepository.findByCrm(agendamentoMedico.getMedico());
        atendimento.setMedico(medico);
        atendimento.setDisponivel(true);
        atendimento.setHorario(agendamentoMedico.getHorario());

        return atendimentoRepository.save(atendimento);
    }

    @GetMapping(value="/atendimento/{crm}")
    public List<Atendimento> getAtendimentosMedico(@PathVariable(value = "crm") String crm){
        Medico medico = medicoRepository.findByCrm(crm);
        return atendimentoRepository.findByMedico(medico);
    }

}
