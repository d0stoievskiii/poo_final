package uff.br.model;

import uff.br.util.Id;
import java.util.List;
import java.io.Serializable;

public class Passagem implements Serializable {

    private Cliente cliente;
    @Id
    private int numero;
    private double price;
    private List<ExecTrecho> etrechos;

    public Passagem(List<ExecTrecho> etrechos, Cliente cliente) {
        this.etrechos = etrechos;
        this.cliente = cliente;
        this.price = 0.0;
        for(int i = 0; i < etrechos.size(); i++){
            this.price += etrechos.get(i).getTrecho().getPrice();
        }
    }

    public String toString() {
        return "ID: " + numero + "\tPreço: " + price + "\n" +
                "De: " + etrechos.get(0).getTrecho().getOrigem() + "\tPara: " + etrechos.get(etrechos.size()-1).getTrecho().getDestino() +  "\n" +
                "Cliente: " + cliente.toString();
    }

    public double calcularMilhas() {
        double total = 0.0;
        for(ExecTrecho etrecho : etrechos){
            total += etrecho.getTrecho().getMilhas();
        }
        return total;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getPrice() {
        return price;
    }

    public List<ExecTrecho> getEtrechos() {
        return etrechos;
    }

    public void setEtrechos(List<ExecTrecho> etrechos) {
        this.etrechos = etrechos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
