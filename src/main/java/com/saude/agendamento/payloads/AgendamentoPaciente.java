package com.saude.agendamento.payloads;

public class AgendamentoPaciente {
    private long codigoPaciente;
    private long codigoAtendimento;

    public long getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(long codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public long getCodigoAtendimento() {
        return codigoAtendimento;
    }

    public void setCodigoAtendimento(long codigoAtendimento) {
        this.codigoAtendimento = codigoAtendimento;
    }
}
