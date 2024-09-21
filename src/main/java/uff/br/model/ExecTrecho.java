package uff.br.model;

import uff.br.util.Id;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.io.Serializable;


public class ExecTrecho implements Serializable {
    @Id
    private int id;
    private Calendar inicio;
    private Calendar fim;
    private Trecho trecho;
    private List<Passagem> passagens;
    private ExecVoo execVoo;

    public ExecTrecho(Calendar inicio, Trecho trecho) {
        this.passagens = new ArrayList<Passagem>();
        this.inicio = inicio;
        this.trecho = trecho;
        Calendar fim = Calendar.getInstance();
        fim.setTime(inicio.getTime());
        int minutos = (int)(trecho.getDuracao()*60);
        fim.add(Calendar.MINUTE, minutos);
        this.fim = fim;
    }

    public String toString() {
        return "IO: " + id + '\n' +
                "Trecho: " + trecho.toString() + '\n' +
                "Passageiros: " + passagens.size() + '\n' +
                "Inicio: " + inicio.getTime().toString() +'\n' +
                "Fim: " + fim.getTime().toString() + '\n';
    }

    public String passagemDetail() {
        return "De: " + trecho.getOrigem() + "\tPara: " + trecho.getDestino() + '\n' +
                "Inicio: " + inicio.getTime().toString() +'\n' +
                "Fim: " + fim.getTime().toString() + '\n';
    }

    public String inicioString() {
        return inicio.getTime().toString();
    }

    public Calendar getInicio() {
        return inicio;
    }

    public void setInicio(Calendar inicio) {
        this.inicio = inicio;
    }

    public Calendar getFim() {
        return fim;
    }

    public void setFim(Calendar fim) {
        this.fim = fim;
    }

    public Trecho getTrecho() {
        return trecho;
    }

    public void setTrecho(Trecho trecho) {
        this.trecho = trecho;
    }

    public List<Passagem> getPassagens() {
        return passagens;
    }

    public int getId() {
        return id;
    }

    public ExecVoo getExecVoo() {
        return execVoo;
    }

    public void setExecVoo(ExecVoo execVoo) {
        this.execVoo = execVoo;
    }
}
