package uff.br;

import uff.br.model.*;
import uff.br.dao.*;
import uff.br.service.*;
import uff.br.util.FabricaDeDaos;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import corejava.Console;

public class Main {
    public static void main(String[] args) {

        PrincipalCliente pCliente = new PrincipalCliente();
        PrincipalExecTrecho pExecTrecho = new PrincipalExecTrecho();
        PrincipalExecVoo pExecVoo = new PrincipalExecVoo();
        PrincipalPassagem pPassagem = new PrincipalPassagem();
        PrincipalTrecho pTrecho = new PrincipalTrecho();
        PrincipalVoo pVoo = new PrincipalVoo();

        recuperarDados();

        boolean flag = true;
        while (flag) {
            System.out.println('\n' + "=================================");
            System.out.println('\n' + "Selecione um submenu:");
            System.out.println('\n' + "1. Clientes");
            System.out.println("2. Passagens");
            System.out.println("3. Voos");
            System.out.println("4. Trechos");
            System.out.println("5. ExecVoos");
            System.out.println("6. ExecTrechos");
            System.out.println("7. Sair");

            int opt = Console.readInt('\n' + "Entre a opção: ");

            switch (opt) {
                case 1 -> {
                    pCliente.Principal();
                }
                case 2 -> {
                    pPassagem.Principal();
                }
                case 3 -> {
                    pVoo.Principal();
                }
                case 4 -> {
                    pTrecho.Principal();
                }
                case 5 -> {
                    pExecVoo.Principal();
                } case 6 -> {
                    pExecTrecho.Principal();
                } case 7 -> {
                    flag = false;
                    salvarDados();
                    System.out.println("Adeus!");
                }
                default -> System.out.println('\n'+ "Opção invalida!");
            }
        }
    }

    private static void recuperarDados() {
        try {
            ClienteDAO clienteDAO = FabricaDeDaos.getDAO(ClienteDAO.class);
            PassagemDAO passDAO = FabricaDeDaos.getDAO(PassagemDAO.class);
            VooDAO vooDAO = FabricaDeDaos.getDAO(VooDAO.class);
            TrechoDAO trechosDAO = FabricaDeDaos.getDAO(TrechoDAO.class);
            ExecVooDAO execVooDAO = FabricaDeDaos.getDAO(ExecVooDAO.class);
            ExecTrechoDAO execTrechoDAO = FabricaDeDaos.getDAO(ExecTrechoDAO.class);

            FileInputStream fis = new FileInputStream(new File("dados.txt"));
            ObjectInputStream ois = new ObjectInputStream(fis);

            Map<Integer, Cliente> mapCliente = (Map<Integer, Cliente>) ois.readObject();
            clienteDAO.setMap(mapCliente);
            Integer cCliente = (Integer) ois.readObject();
            clienteDAO.setContador(cCliente);

            Map<Integer, Passagem> mapPassagem = (Map<Integer, Passagem>) ois.readObject();
            passDAO.setMap(mapPassagem);
            Integer cPassagem = (Integer) ois.readObject();
            passDAO.setContador(cPassagem);

            Map<Integer, Voo> mapVoo = (Map<Integer, Voo>) ois.readObject();
            vooDAO.setMap(mapVoo);
            Integer cVoo = (Integer) ois.readObject();
            vooDAO.setContador(cVoo);

            Map<Integer, Trecho> mapTrecho = (Map<Integer, Trecho>) ois.readObject();
            trechosDAO.setMap(mapTrecho);
            Integer cTrecho = (Integer) ois.readObject();
            trechosDAO.setContador(cTrecho);

            Map<Integer, ExecVoo> mapExecVoo = (Map<Integer, ExecVoo>) ois.readObject();
            execVooDAO.setMap(mapExecVoo);
            Integer cExecVoo = (Integer) ois.readObject();
            execVooDAO.setContador(cExecVoo);

            Map<Integer, ExecTrecho> mapExecTrecho = (Map<Integer, ExecTrecho>) ois.readObject();
            execTrechoDAO.setMap(mapExecTrecho);
            Integer cExecTrecho = (Integer) ois.readObject();
            execTrechoDAO.setContador(cExecTrecho);
            
        } catch (FileNotFoundException e) { System.out.println("Arquivo criado!");
        } catch (IOException | ClassNotFoundException e) { throw new RuntimeException(e);
        }
    }

    private static void salvarDados() {
        ClienteDAO clienteDAO = FabricaDeDaos.getDAO(ClienteDAO.class);
        PassagemDAO passDAO = FabricaDeDaos.getDAO(PassagemDAO.class);
        VooDAO vooDAO = FabricaDeDaos.getDAO(VooDAO.class);
        TrechoDAO trechosDAO = FabricaDeDaos.getDAO(TrechoDAO.class);
        ExecVooDAO execVooDAO = FabricaDeDaos.getDAO(ExecVooDAO.class);
        ExecTrechoDAO execTrechoDAO = FabricaDeDaos.getDAO(ExecTrechoDAO.class);

        Map<Integer, Cliente> mapCliente = clienteDAO.getMap();
        Map<Integer, Passagem> mapPassagem = passDAO.getMap();
        Map<Integer, Voo> mapVoo = vooDAO.getMap();
        Map<Integer, Trecho> mapTrecho = trechosDAO.getMap();
        Map<Integer, ExecVoo> mapExecVoo = execVooDAO.getMap();
        Map<Integer, ExecTrecho> mapExecTrecho = execTrechoDAO.getMap();

        Integer cCliente = clienteDAO.getContador();
        Integer cPassagem = passDAO.getContador();
        Integer cVoo = vooDAO.getContador();
        Integer cTrecho = trechosDAO.getContador();
        Integer cExecVoo = execVooDAO.getContador();
        Integer cExecTrecho = execTrechoDAO.getContador();

        try {
            FileOutputStream fos = new FileOutputStream(new File("dados.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(mapCliente);
            oos.writeObject(cCliente);

            oos.writeObject(mapPassagem);
            oos.writeObject(cPassagem);

            oos.writeObject(mapVoo);
            oos.writeObject(cVoo);

            oos.writeObject(mapTrecho);
            oos.writeObject(cTrecho);

            oos.writeObject(mapExecVoo);
            oos.writeObject(cExecVoo);

            oos.writeObject(mapExecTrecho);
            oos.writeObject(cExecTrecho);
            
        } catch (FileNotFoundException e) { System.out.println(e.getMessage()) ;
        } catch (IOException e) { throw new RuntimeException(e); }
        
    }

    private static void montarBanco() {
        List<Trecho> trechos = new ArrayList<Trecho>();
        List<Voo> voos = new ArrayList<Voo>();
        VooService vs = new VooService();
        TrechoService ts = new TrechoService();
        ExecVooService evs = new ExecVooService();
        ExecTrechoService ets = new ExecTrechoService();

        String rio = "Rio de Janeiro";
        String sp = "Guarulhos";
        String poa = "Porto Alegre";
        String st = "Santos";
        String svd = "Salvador";

        trechos.add(new Trecho(rio, sp, 200.0, 250, 1));
        trechos.add(new Trecho(sp, poa, 250, 500, 2));
        trechos.add(new Trecho(sp, st, 200, 100, 0.5));
        trechos.add(new Trecho(sp, rio, 230, 250, 1));
        trechos.add(new Trecho(poa, sp, 210, 500, 2));
        trechos.add(new Trecho(st, svd, 400, 500, 2));
        trechos.add(new Trecho(sp, svd, 450, 600, 3));
        trechos.add(new Trecho(svd, sp, 450, 600, 3));
        trechos.add(new Trecho(st, sp, 200, 100, 0.5));

        for(Trecho t : trechos) {
            ts.incluir(t);
        }

        trechos = new ArrayList<Trecho>();
        trechos.add(new Trecho(rio, sp, 200.0, 250, 1));
        trechos.add(new Trecho(sp, poa, 250, 500, 2));
        voos.add(new Voo(trechos));

        trechos = new ArrayList<Trecho>();
        trechos.add(new Trecho(sp, st, 200, 100, 0.5));
        voos.add(new Voo(trechos));

        trechos = new ArrayList<Trecho>();
        trechos.add(new Trecho(sp, rio, 230, 250, 1));
        voos.add(new Voo(trechos));

        trechos = new ArrayList<Trecho>();
        trechos.add(new Trecho(poa, sp, 210, 500, 2));
        voos.add(new Voo(trechos));

        trechos = new ArrayList<Trecho>();
        trechos.add(new Trecho(sp, st, 200, 100, 0.5));
        trechos.add(new Trecho(st, svd, 400, 500, 2));
        voos.add(new Voo(trechos));

        trechos = new ArrayList<Trecho>();
        trechos.add(new Trecho(sp, st, 200, 100, 0.5));
        trechos.add(new Trecho(st, svd, 400, 500, 2));
        voos.add(new Voo(trechos));

        trechos = new ArrayList<Trecho>();
        trechos.add(new Trecho(svd, sp, 450, 600, 3));
        voos.add(new Voo(trechos));

        for(Voo v: voos) {
            vs.incluir(v);
        }
    }
}