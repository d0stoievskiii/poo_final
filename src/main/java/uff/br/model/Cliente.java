package uff.br.model;

import uff.br.util.Id;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;


public class Cliente implements Serializable {
    @Id
    private int id;
    private String nome;
    private String cpf;
    private List<Passagem> passagens;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.passagens = new ArrayList<Passagem>();
    }

    public String toString() {
        return "==================================\n" +
                "Nome: " + nome + "\nCPF: " + cpf +
                "\n==================================";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Passagem> getPassagens() {
        return passagens;
    }
}
