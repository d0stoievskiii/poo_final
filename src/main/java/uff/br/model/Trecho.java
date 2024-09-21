package uff.br.model;

import java.text.NumberFormat;
import uff.br.util.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.io.Serializable;

public class Trecho implements Serializable {
    @Id
    private int id;
    private String origem;
    private String destino;
    private double price;
    private double milhas;
    private double duracao;
    private List<ExecTrecho> execTrechos;

    private static final NumberFormat NF;

    static {
        NF = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    }

    public Trecho(String origem, String destino, double price, double milhas, double duracao) {
        this.duracao = duracao;
        this.origem = origem;
        this.destino = destino;
        this.price = price;
        this.milhas = milhas;
        this.execTrechos = new ArrayList<ExecTrecho>();
    }

    public String toString() {
        return "de: " + origem + ", para: " + destino + ", por: " + getPriceMasc() + ", milhas: " + milhas;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPriceMasc() {
        return NF.format(price);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ExecTrecho> getExecTrechos() {
        return execTrechos;
    }

    public double getMilhas() {
        return milhas;
    }

    public void setMilhas(double milhas) {
        this.milhas = milhas;
    }

    public double getDuracao() {
        return duracao;
    }
}
