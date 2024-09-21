package uff.br.model;

import uff.br.util.Id;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.io.Serializable;

public class Voo implements Serializable {
    @Id
    private int id;
    private String origem;
    private String destino;
    private List<Trecho> trechos;
    private List<ExecVoo> execvoos;

    private static final NumberFormat NF;

    static {
        NF = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    }

    public Voo(List<Trecho> trechos) {
        this.execvoos = new ArrayList<ExecVoo>();
        this.trechos = trechos;
        this.origem = trechos.get(0).getOrigem();
        this.destino = trechos.get(trechos.size() -1).getDestino();
        this.execvoos = new ArrayList<ExecVoo>();
    }

    public String escalas() {
        StringBuilder resp = new StringBuilder(origem);
        for(Trecho t: trechos) {
            String str = "->" + t.getDestino();
            resp.append(str);
        }
        return resp.toString();
    }

    public String toString() {
        return "ID(Voo): " + id + '\n' +
                "Origem: " + origem + '\n' +
                "Destino: " + destino + '\n' +
                "Escalas: " + escalas();
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public List<Trecho> getTrechos() {
        return trechos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ExecVoo> getExecvoos() {
        return execvoos;
    }
}
