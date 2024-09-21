package uff.br.model;

import uff.br.util.Id;
import java.util.Calendar;
import java.util.List;
import java.io.Serializable;

public class ExecVoo implements Serializable {
    @Id
    private int id;
    private Voo voo;
    private Calendar inicio;
    private Calendar fim;
    private List<ExecTrecho> etrechos;

    public ExecVoo(Voo voo, List<ExecTrecho> etrechos) {
        this.voo = voo;
        this.inicio = etrechos.get(0).getInicio();
        this.fim = etrechos.get(etrechos.size() - 1).getFim();
        this.etrechos = etrechos;
    }

    public String toString() {
        return "ID(Exec): " + id +'\n' + voo.toString() + "\n" + inicio.getTime().toString() + "\n" + fim.getTime().toString();
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
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

    public List<ExecTrecho> getEtrechos() {
        return etrechos;
    }

    public void setEtrechos(List<ExecTrecho> etrechos) {
        this.etrechos = etrechos;
    }

    public int getId() {
        return id;
    }
}
