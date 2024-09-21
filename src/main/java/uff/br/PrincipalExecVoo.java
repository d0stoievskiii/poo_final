package uff.br;

import uff.br.exception.DataErradaException;
import uff.br.exception.ObjetoNaoEncontradoException;
import uff.br.model.*;
import uff.br.service.ExecTrechoService;
import uff.br.service.ExecVooService;
import uff.br.service.VooService;
import uff.br.service.PassagemService;
import corejava.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class PrincipalExecVoo {
    private final VooService vs = new VooService();
    private final ExecVooService evs = new ExecVooService();
    private final ExecTrechoService ets = new ExecTrechoService();
    private final PassagemService ps = new PassagemService();

    private boolean assertParametros(int num, int min, int max) {
        return !(num >= min && num <= max);
    }

    private Calendar entraData(Calendar c1) {
        Calendar data = Calendar.getInstance();
        int ano = Console.readInt("Entre o ano: ");
        int mes = Console.readInt("Entre o mes(1-12): ");
        int dia = Console.readInt("Entre o dia: ");
        int hora = Console.readInt("Entre o hora: ");
        int minuto = Console.readInt("Entre o minuto: ");
        data.set(Calendar.YEAR, ano);
        data.set(Calendar.MONTH, mes-1);
        if (assertParametros(mes, 1, 12) || assertParametros(dia, 1, data.getActualMaximum(Calendar.DAY_OF_MONTH)) || assertParametros(hora, 0, 23) || assertParametros(minuto, 0, 59)) {
            System.out.println("Valor invalido para campo.");
            System.out.println("Vamos tentar de novo...");
            return entraData(c1);
        }
        data.set(Calendar.DAY_OF_MONTH, dia);
        data.set(Calendar.HOUR_OF_DAY, hora);
        data.set(Calendar.MINUTE, minuto);
        if (c1 != null) {
            if (data.compareTo(c1) < 0) {
                System.out.println("[ERRO] Data precisa ser posterior a " + c1.getTime().toString());
                return entraData(c1);
            }
        }
        return data;
    }

    public void Principal() {
        List<ExecTrecho> etrechos;
        List<ExecVoo> evoos;
        Calendar tinicio;
        Voo voo;
        ExecVoo execVoo;
        ExecTrecho execTrecho;

        boolean flag = true;
        while (flag) {
            tinicio = null;


            System.out.println('\n' + "===========================");
            System.out.println('\n' + "1. Cadastrar nova Execução de Voo");
            System.out.println("2. Remover uma Execuçao de Voo.");
            System.out.println("3. Listar todas as execuções de Voo.");
            System.out.println("4. Relatório de Execução de Voo.");
            System.out.println("5. Voltar");

            int opt = Console.readInt('\n' + "Entre uma opção: ");
            System.out.println();
            
            switch (opt) {
                case 1 -> {
                    etrechos = new ArrayList<ExecTrecho>();
                    int id = Console.readInt("Informe a ID do Voo: ");
                    try {
                        voo = vs.recuperarVooPorId(id);
                    } catch (ObjetoNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println('\n' + "Vamos cadastrar as Execuções de Trecho do Voo:");
                    List<Trecho> trechos = voo.getTrechos();
                    for (Trecho trecho : trechos) {
                        System.out.println('\n' + "Para o trecho:");
                        System.out.println(trecho);
                        System.out.println("Data de inicio:");
                        tinicio = entraData(tinicio);
                        execTrecho = new ExecTrecho(tinicio, trecho);
                        ets.incluir(execTrecho);
                        etrechos.add(execTrecho);
                        System.out.println("Exec trecho cadastrado!");
                    }
                    execVoo = new ExecVoo(voo, etrechos);
                    for(ExecTrecho et : etrechos) {
                        et.setExecVoo(execVoo);
                    }
                    evs.incluir(execVoo);
                    System.out.println("Execução de Voo cadastrada!");
                } case 2 -> {
                    int id = Console.readInt("Informe a ID da ExecVoo: ");
                    try {
                        execVoo = evs.remover(id);
                        etrechos = execVoo.getEtrechos();
                        for(ExecTrecho et : etrechos) {
                            ets.remover(et.getId());
                            List<Passagem> passagens = et.getPassagens();
                            for (Passagem passagem : passagens) {
                                ps.remover(passagem.getNumero());
                            }
                        }
                    } catch (ObjetoNaoEncontradoException e) { System.out.println(e.getMessage()); }
                } case 3 -> {
                    evoos = evs.recuperarExecVoos();
                    for(ExecVoo ev : evoos) {
                        System.out.println(ev);
                        System.out.println();
                    }
                } case 4 -> {
                    int id = Console.readInt("Entre a id do Voo:");
                    try {
                        voo = vs.recuperarVooPorId(id);
                        int ano = Console.readInt("Entre o ano: ");
                        int mes = Console.readInt("Entre o mes(1-12): ");
                        int dia = Console.readInt("Entre o dia: ");
                        execVoo = evs.recuperarExecPorVooData(voo, ano, mes, dia);
                        System.out.println();
                        System.out.println(execVoo);
                        System.out.println();
                        etrechos = execVoo.getEtrechos();
                        for(ExecTrecho et : etrechos) {
                            System.out.println(et);
                        }
                    } catch (ObjetoNaoEncontradoException e) { System.out.println(e.getMessage()); }

                } case 5 -> {
                    flag = false;
                } case 6 -> {
                    //regristraEmMassa(2024, 7, 1);
                }
                default -> { System.out.println("Opção inválida!"); }
            }
        }
    }

    private void regristraEmMassa(int ano, int mes, int dia) {
        List<Voo> voos = vs.recuperarVoos();
        List<Trecho> trechos;
        int h =5;


        for(Voo v: voos) {
            trechos = v.getTrechos();

            Calendar inicio = Calendar.getInstance();
            inicio.set(ano, mes-1, dia, h++, 0);
            for(int i = 0; i < 60; i++) {
                List<ExecTrecho> etrechos = new ArrayList<ExecTrecho>();

                for(Trecho t: trechos) {
                    etrechos.add(new ExecTrecho(inicio, t));
                    inicio.add(Calendar.HOUR, (int)(t.getDuracao()+1));
                }
                ExecVoo evoo = new ExecVoo(v, etrechos);
                for(ExecTrecho et: etrechos) {
                    et.setExecVoo(evoo);
                    ets.incluir(et);
                }
                evs.incluir(evoo);
                inicio.add(Calendar.HOUR, 12);
            }
        }
    }
}
