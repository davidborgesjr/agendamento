package com.saude.agendamento.payloads;

public class ResultadoAutenticacao {
    private boolean status;
    private String mensagem;
    private long codigoPaciente;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(long codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }
}
