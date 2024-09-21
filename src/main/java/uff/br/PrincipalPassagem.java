package uff.br;

import uff.br.exception.ObjetoNaoEncontradoException;
import uff.br.model.Passagem;
import uff.br.model.Cliente;
import uff.br.model.ExecTrecho;
import uff.br.model.Trecho;
import uff.br.service.PassagemService;
import uff.br.service.ClienteService;
import uff.br.service.ExecTrechoService;
import uff.br.service.TrechoService;
import corejava.Console;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PrincipalPassagem {
    private final PassagemService passagemService = new PassagemService();
    private final ClienteService clienteService = new ClienteService();
    private final ExecTrechoService execTrechoService = new ExecTrechoService();
    private final TrechoService trechoService = new TrechoService();

    public void Principal() {
        Cliente cliente;
        Passagem passagem;
        ExecTrecho execTrecho;
        Trecho trecho;
        List<ExecTrecho> execTrechos;
        List<Passagem> passagens;
        List<Trecho> trechos;
        List<Trecho> destinos;
        String origem;
        String destino;
        String cpf;

        boolean flag = true;
        while (flag) {
            execTrechos  = new ArrayList<ExecTrecho>();
            trechos  = new ArrayList<Trecho>();

            System.out.println('\n' + "===========================");
            System.out.println('\n' + "1. Cadastrar nova passagem.");
            System.out.println("2. Remover passagem.");
            System.out.println("3. Listar todas as passagens");
            System.out.println("4. Listar todas as passagens de um cliente");
            System.out.println("5. Relatório de passagem.");
            System.out.println("6. Voltar");

            int opt = Console.readInt('\n' + "Entre uma opção: ");
            System.out.println();
            switch (opt) {
                case 1 -> {
                    cpf = Console.readLine("Entre o CPF do cliente: ");
                    try {
                        cliente = clienteService.recuperarClientePorCpf(cpf);
                        origem = Console.readLine("Entre aeroporto de origem: ");
                        destino = Console.readLine("Entre aeroporto de destino: ");
                        try {
                            trecho = trechoService.recuperarTrechoPorOrigemDestino(origem, destino);
                            trechos.add(trecho);
                            System.out.println("Passagem para o trecho:");
                            System.out.println(trecho);
                        } catch (ObjetoNaoEncontradoException e) {
                            destinos = trechoService.recuperarTrechosPorDestino(destino);
                            System.out.println("Opções de percurso:");
                            int i = 1;
                            for(Trecho t: destinos) {
                                try {
                                    trecho = trechoService.recuperarTrechoPorOrigemDestino(origem, t.getOrigem());
                                    double price = trecho.getPrice();
                                    price += t.getPrice();
                                    System.out.println(i + ". " + origem + "->" + trecho.getDestino() + "->" + destino + "\tPreço: " + price);
                                    i++;
                                } catch (ObjetoNaoEncontradoException f) {
                                    //nada
                                    continue;
                                }
                            }
                            int escala = Console.readInt("Entre sua escolha(numero): ");
                            if (escala > i || escala < 1) {
                                System.out.println("Input invalido, tente novamente.");
                                break;
                            }
                            int j = 1;
                            for(Trecho t: destinos) {
                                try {
                                    trecho = trechoService.recuperarTrechoPorOrigemDestino(origem, t.getOrigem());
                                    if (j == escala) {
                                        trechos.add(trecho);
                                        trechos.add(t);
                                        break;
                                    }
                                    j++;
                                } catch (ObjetoNaoEncontradoException f) {
                                    //nada
                                    continue;
                                }
                            }

                        }
                        trecho = trechos.get(0);
                        Calendar now = Calendar.getInstance();
                        now.set(2024, Calendar.JULY, 1);
                        List<ExecTrecho> execs = execTrechoService.recuperarExecPorTrechoParaVenda(trecho, now);
                        int k = 1;
                        for(ExecTrecho exec: execs) {
                            System.out.println(k + ". " + exec.inicioString());
                            k++;
                        }
                        int selec = Console.readInt("Entre sua escolha(numero): ");
                        if (selec > k || selec < 1) {
                            System.out.println("Input invalido, tente novamente.");
                            break;
                        }
                        execTrechos.add(execs.get(selec-1));
                        if (trechos.size() > 1) {
                            for(int c = 1; c < trechos.size(); c++) {
                                trecho = trechos.get(c);
                                Calendar now2 = execTrechos.get(execTrechos.size()-1).getFim();
                                execs = execTrechoService.recuperarExecPorTrechoParaVenda(trecho, now2);
                                execTrechos.add(execs.get(0));
                            }
                        }
                        passagem = new Passagem(execTrechos, cliente);
                        passagemService.incluir(passagem);
                        System.out.println("Passagem cadastrada com sucesso!");
                        System.out.println(passagem);

                    } catch (ObjetoNaoEncontradoException e) { System.out.println(e.getMessage()); }
                } case 2 -> {
                    int id = Console.readInt("Entre o id da passagem a ser removida: ");
                    try {
                        passagem = passagemService.remover(id);
                        System.out.println("Passagem:");
                        System.out.println(passagem);
                        System.out.println("Removida com sucesso!");
                    } catch (ObjetoNaoEncontradoException e) { System.out.println(e.getMessage()); }
                } case 3 -> {
                    passagens = passagemService.recuperarPassagens();
                    for(Passagem p : passagens) {
                        System.out.println(p);
                        System.out.println();
                    }
                } case 4 -> {
                    cpf = Console.readLine("Entre com o cpf do cliente: ");
                    try {
                        cliente = clienteService.recuperarClientePorCpf(cpf);
                        passagens = cliente.getPassagens();
                        for(Passagem p : passagens) {
                            System.out.println(p);
                            System.out.println();
                        }
                    } catch (ObjetoNaoEncontradoException e) { System.out.println(e.getMessage()); }
                } case 5 -> {
                    int id = Console.readInt("Entre o ID da passagem: ");
                    try {
                        passagem = passagemService.recuperarPassagemPorId(id);
                        System.out.println();
                        System.out.println(passagem);
                        execTrechos = passagem.getEtrechos();
                        System.out.println("======Execuções de trecho======");
                        for(ExecTrecho exec : execTrechos) {
                            System.out.println(exec.passagemDetail());
                        }
                        System.out.println("======Execuções de trecho======");
                    } catch (ObjetoNaoEncontradoException e) { System.out.println(e.getMessage()); }
                } case 6 -> {
                    flag = false;
                    System.out.println("Retornando...");
                } default -> {
                    System.out.println("Opção invalida!");
                }
            }
        }
    }
}
