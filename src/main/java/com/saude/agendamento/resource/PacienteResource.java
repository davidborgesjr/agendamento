package com.saude.agendamento.resource;

import com.saude.agendamento.model.*;
import com.saude.agendamento.payloads.AgendamentoMedico;
import com.saude.agendamento.payloads.AgendamentoPaciente;
import com.saude.agendamento.payloads.Autenticacao;
import com.saude.agendamento.payloads.ResultadoAutenticacao;
import com.saude.agendamento.repository.AtendimentoRepository;
import com.saude.agendamento.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/pacientes")
public class PacienteResource {

    @Autowired
    private AtendimentoRepository atendimentoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping("/atendimento/{codigo}")
    public List<Atendimento> getAtendimentosPaciente(@PathVariable (value="codigo") long codigo){
        Paciente paciente = pacienteRepository.findById(codigo);
        return atendimentoRepository.findByPaciente(paciente);
    }

    @PostMapping("/cadastro")
    public Paciente setPaciente(@RequestBody Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    @PostMapping("/autenticar")
    public ResultadoAutenticacao getAutenticacao(@RequestBody Autenticacao autenticacao){
        Paciente paciente = pacienteRepository.findByEmailEquals(autenticacao.getEmail());
        ResultadoAutenticacao resultadoAutenticacao = new ResultadoAutenticacao();

        if(paciente == null){
            resultadoAutenticacao.setStatus(false);
            resultadoAutenticacao.setMensagem("O e-mail informado não está cadastrado");
            return resultadoAutenticacao;
        }

        if(paciente.getEmail().equals(autenticacao.getEmail()) && paciente.getSenha().equals(autenticacao.getSenha())){
            resultadoAutenticacao.setCodigoPaciente(paciente.getId());
            resultadoAutenticacao.setStatus(true);
            resultadoAutenticacao.setMensagem("Usuário autenticado com sucesso");
        }else{
            resultadoAutenticacao.setStatus(false);
            resultadoAutenticacao.setMensagem("Usuário e/ou senha inválidos");
        }

        return resultadoAutenticacao;
    }

    @PostMapping("/agendar")
    public Atendimento setAtendimento(@RequestBody AgendamentoPaciente agendamentoPaciente){
        Atendimento atendimento = atendimentoRepository.findById(agendamentoPaciente.getCodigoAtendimento());
        Paciente paciente = pacienteRepository.findById(agendamentoPaciente.getCodigoPaciente());
        atendimento.setDisponivel(false);
        atendimento.setPaciente(paciente);
        return atendimentoRepository.save(atendimento);
    }

}
