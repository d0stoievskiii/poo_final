package uff.br;

import uff.br.exception.ObjetoNaoEncontradoException;
import uff.br.exception.TrechoComExecException;
import uff.br.model.Trecho;
import uff.br.service.TrechoService;
import corejava.Console;

import java.util.List;

public class PrincipalTrecho {
    private final TrechoService ts = new TrechoService();

    public void Principal() {
        String origem;
        String destino;
        double preco;
        double milhas;
        double dura;
        Trecho t;

        boolean flag = true;
        while (flag) {
            System.out.println('\n' + "===========================");
            System.out.println('\n' + "1. Cadastrar um novo Trecho");
            System.out.println("2. Remover um Trecho.");
            System.out.println("3. Listar todos os Trechos.");
            System.out.println("4. Encontrar trecho.");
            System.out.println("5. Voltar.");

            int opt = Console.readInt('\n' + "Entre uma opção:");
            System.out.println();
            switch (opt) {
                case 1 -> {
                    origem = Console.readLine("Entre a origem do trecho:");
                    destino = Console.readLine("Entre o destino do trecho:");
                    try {
                        t = ts.recuperarTrechoPorOrigemDestino(origem, destino);
                        System.out.println("Trecho já existe!");
                        System.out.println(t);
                    } catch (ObjetoNaoEncontradoException e) {
                        preco = Console.readDouble("Entre o preço para o novo trecho:");
                        milhas = Console.readDouble("Entre a distancia em milhas do trecho:");
                        dura = Console.readDouble("Entre a duração em horas do trecho: ");

                        t = new Trecho(origem, destino, preco, milhas, dura);
                        ts.incluir(t);
                        System.out.println("Trecho cadastrado com sucesso!");
                    }
                } case 2 -> {
                    System.out.println('\n' + "1. Encontrar trecho por id.");
                    System.out.println("2. Encontrar trecho por origem-destino.");
                    int opt2 = Console.readInt('\n' + "Entre uma opção: ");
                    try {
                        switch (opt2) {
                            case 1 -> {
                                int id = Console.readInt("Informe o id do trecho: ");
                                ts.remover(id);
                                System.out.println("Trecho removido com sucesso!");
                            } case 2 -> {
                                origem = Console.readLine("Entre a origem do trecho:");
                                destino = Console.readLine("Entre o destino do trecho:");
                                t = ts.recuperarTrechoPorOrigemDestino(origem, destino);
                                ts.remover(t.getId());
                            }default -> { System.out.println("Opção inválida."); }
                        }
                    } catch (ObjetoNaoEncontradoException | TrechoComExecException e) { System.out.println('\n' + e.getMessage()); }
                } case 3 -> {
                    List<Trecho> trechos = ts.recuperarTrechos();
                    System.out.println('\n' + "====================");
                    System.out.println("Trechos:");
                    for (Trecho trecho : trechos) {
                        System.out.println(trecho);
                    }
                    System.out.println('\n' + "====================");
                } case 4 -> {
                    origem = Console.readLine("Entre a origem do trecho:");
                    destino = Console.readLine("Entre o destino do trecho:");
                    try {
                        t = ts.recuperarTrechoPorOrigemDestino(origem, destino);
                        System.out.println("Trecho encontrado!");
                        System.out.println(t);
                    } catch (ObjetoNaoEncontradoException e) { System.out.println('\n' + e.getMessage()); }
                } case 5 -> {
                    flag = false;
                    System.out.println("Voltando ao menu principal!");
                } default -> { System.out.println("Opção inválida!");}
            }
        }

    }

}
