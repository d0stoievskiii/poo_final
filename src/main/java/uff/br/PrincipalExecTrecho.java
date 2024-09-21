package uff.br;



import uff.br.exception.ObjetoNaoEncontradoException;
import uff.br.model.ExecTrecho;
import uff.br.model.ExecVoo;
import uff.br.model.Passagem;
import uff.br.model.Trecho;
import uff.br.service.PassagemService;
import uff.br.service.ExecVooService;
import uff.br.service.ExecTrechoService;
import corejava.Console;
import uff.br.service.TrechoService;

import java.util.List;

public class PrincipalExecTrecho {
    private final ExecTrechoService ets = new ExecTrechoService();
    private final TrechoService ts = new TrechoService();

    public void Principal() {
        ExecTrecho execTrecho;
        ExecVoo execVoo;
        Passagem passagem;
        Trecho trecho;
        List<ExecTrecho> execTrechos;
        String origem;
        String destino;

        boolean flag = true;
        while (flag) {
            System.out.println('\n' + "============================================================================================");
            System.out.println("ATENÇÃO! Devido a natureza da execução de um trecho, a criação e remoção destas");
            System.out.println("é feita através do Menu para administração de execuções de voo");
            System.out.println("já que a execução de um trecho está intrinsincamente ligada a existência da execução de um Voo");
            System.out.println('\n' + "===========================================================================================");

            System.out.println('\n' + "1. Listar todas as execuções de Trecho");
            //System.out.println("2. Listar todas as execuções de UM Trecho");
            System.out.println("3. Relatório de uma Execução de Trecho.");
            System.out.println("4. Voltar.");

            int opt = Console.readInt('\n' + "Entre uma opção: ");
            System.out.println();
            switch (opt) {
                case 1 -> {
                    execTrechos = ets.recuperarExecTrechos();
                    for(ExecTrecho et : execTrechos) {
                        System.out.println(et);
                        //Trecho t = et.getTrecho();
                        //t.getExecTrechos().add(et);
                    }
                } case 2 -> {
                    System.out.println('\n' + "=====================");
                    System.out.println("1. Encontrar trecho por ID.");
                    System.out.println("2. Encontrar trecho por Origem-Destino.");

                    int opt2 = Console.readInt("Entre uma opção: ");
                    trecho = null;
                    try {
                        switch (opt2) {
                            case 1 -> {
                                int id = Console.readInt("Informe o ID do TRECHO: ");
                                trecho = ts.recuperarTrechoPorId(id);
                            } case 2 -> {
                                origem = Console.readLine("Informe a origem: ");
                                destino = Console.readLine("Informe o destino: ");
                                trecho = ts.recuperarTrechoPorOrigemDestino(origem, destino);
                            } default -> {
                                System.out.println("Opção inválida, tente novamente.");
                            }
                        }
                    } catch (ObjetoNaoEncontradoException e) { System.out.println(e.getMessage()); }
                    if (trecho != null) {
                        execTrechos = trecho.getExecTrechos();
                        for(ExecTrecho et : execTrechos) {
                            System.out.println(et);
                        }
                    }

                } case 3 -> {
                    int id = Console.readInt("Entre a ID do ExecTrecho: ");
                    try {
                        execTrecho = ets.recuperarExecTrechoPorId(id);
                        System.out.println(execTrecho);
                    } catch (ObjetoNaoEncontradoException e) { System.out.println(e.getMessage()); }
                } case 4 -> {
                    flag = false;
                }default -> {
                    System.out.println("Opção inválida, tente novamente.");
                }
            }

        }

    }
}
