package uff.br;

import uff.br.exception.ObjetoNaoEncontradoException;
import uff.br.exception.VooComExecException;
import uff.br.service.TrechoService;
import uff.br.service.VooService;
import uff.br.model.Voo;
import uff.br.model.Trecho;
import corejava.Console;

import java.util.ArrayList;
import java.util.List;


public class PrincipalVoo {

    private final VooService vooService = new VooService();
    private final TrechoService trechoService = new TrechoService();

    public void Principal() {


        double preco;
        double milhas;
        double dura;
        Voo voo;

        boolean flag = true;
        while (flag) {
            List<Trecho> trechos = new ArrayList<Trecho>();
            String origem;
            String destino = null;
            System.out.println('\n' + "===========================");
            System.out.println('\n' + "1. Cadastrar um novo Voo");
            System.out.println("2. Remover um Voo.");
            System.out.println("3. Listar todos os Voos.");
            System.out.println("4. Listar todos os Voos saindo de uma cidade.");
            System.out.println("5. Voltar.");

            int opt = Console.readInt('\n' + "Entre uma opção:");
            System.out.println();

            switch (opt) {

                case 1 -> {
                    System.out.println("Vamos cadastrar os trechos do Voo:");
                    boolean flag2 = true;
                    do {
                        Trecho trecho;
                        if (destino == null) {
                            origem = Console.readLine("Entre a origem do voo: ");
                        } else {
                            origem = destino;
                        }
                        destino = Console.readLine("Entre o destino do trecho: ");
                        try {
                            trecho = trechoService.recuperarTrechoPorOrigemDestino(origem, destino);
                        } catch (ObjetoNaoEncontradoException e) {
                            preco = Console.readDouble("Entre o preço para o novo trecho: ");
                            milhas = Console.readDouble("Entre a distancia em milhas do trecho: ");
                            dura = Console.readDouble("Entre a duração em horas do trecho: ");
                            trecho = new Trecho(origem, destino, preco, milhas, dura);
                            trechoService.incluir(trecho);
                        }
                        trechos.add(trecho);
                        System.out.println("Trecho adicionado com sucesso!");

                        System.out.println('\n' + "1. Cadastrar proximo trecho.");
                        System.out.println("2. Todos os trechos foram cadastrados.");

                        int s2 = Console.readInt("Entre uma opção:");
                        switch (s2) {
                            case 1 -> {}
                            case 2 -> {
                                flag2 = false;
                            }
                        }
                    } while (flag2);
                    voo = new Voo(trechos);
                    vooService.incluir(voo);
                    System.out.println('\n' + "Voo cadastrado com sucesso!");
                    
                } case 2 -> {
                    int id = Console.readInt("Entre o id do voo que você deseja remover:");
                    try {
                        vooService.remover(id);
                        System.out.println('\n' + "Voo removido com sucesso!");
                    } catch (ObjetoNaoEncontradoException | VooComExecException e) { System.out.println('\n' + e.getMessage()); ;}

                } case 3 -> {
                    List<Voo> voos = vooService.recuperarVoos();
                    for (Voo v : voos) {
                        System.out.println(v);
                    }
                } case 4 -> {
                    origem = Console.readLine("Entre o aeroporto de origem:");
                    List<Voo> voos = vooService.recuperarVooPorOrigem(origem);
                    for (Voo v : voos) {
                        System.out.println(v);
                    }
                } case 5 -> {
                    flag = false;
                } default -> {
                    System.out.println("Opção inválida.");
                }
            }
        }
    }
}
