package uff.br;

import uff.br.exception.ClienteComPassagensExeception;
import uff.br.exception.ObjetoJaCadastradoException;
import uff.br.exception.ObjetoNaoEncontradoException;
import uff.br.model.Passagem;
import uff.br.model.Cliente;
import uff.br.model.ExecTrecho;
import uff.br.service.ClienteService;
import corejava.Console;

import java.util.Calendar;
import java.util.List;

public class PrincipalCliente {
    private final ClienteService clienteService = new ClienteService();

    public void Principal() {
        Passagem passagem;
        Cliente cliente;
        List<Passagem> passagens;
        List<Cliente> clientes;
        String nome;
        String cpf;

        boolean flag = true;
        while (flag) {
            System.out.println('\n' + "===========================");
            System.out.println('\n' + "1. Cadastrar novo cliente.");
            System.out.println("2. Remover cadastro de cliente.");
            System.out.println("3. Listar todos os clientes.");
            System.out.println("4. Buscar cliente por CPF.");
            System.out.println("5. Alterar cliente.");
            System.out.println("6. Voltar");

            int opt = Console.readInt('\n' + "Entre uma opção: ");
            System.out.println();
            switch (opt) {
                case 1 -> {
                    nome = Console.readLine("Digite o nome do cliente: ");
                    cpf = Console.readLine("Digite o CPF: ");
                    cliente = new Cliente(nome, cpf);
                    try {
                        clienteService.incluir(cliente);
                        System.out.println("Cliente cadastrado com sucesso!");
                    } catch (ObjetoJaCadastradoException e) { System.out.println(e.getMessage()); }
                } case 2 -> {
                    int id = Console.readInt("Digite o id do cliente: ");
                    try {
                        cliente = clienteService.remover(id);
                        System.out.println("Cliente:");
                        System.out.println(cliente);
                        System.out.println("Removido com sucesso!");
                    } catch (ObjetoNaoEncontradoException | ClienteComPassagensExeception e) { System.out.println(e.getMessage()); }
                } case 3 -> {
                    clientes = clienteService.recuperarClientes();
                    for(Cliente c : clientes) {
                        System.out.println(c);
                    }
                } case 4 -> {
                    cpf = Console.readLine("Digite o CPF: ");
                    try {
                        cliente = clienteService.recuperarClientePorCpf(cpf);
                        System.out.println(cliente);
                        passagens = cliente.getPassagens();
                        double milhas = 0.0;
                        Calendar now = Calendar.getInstance();
                        for(Passagem p : passagens) {
                            if (p.getEtrechos().get(p.getEtrechos().size()-1).getFim().compareTo(now) <= 0) {
                                milhas += p.calcularMilhas();
                            }
                        }
                        System.out.println("Milhagem total: " + milhas);
                    } catch (ObjetoNaoEncontradoException e) { System.out.println(e.getMessage()); }
                } case 5 -> {
                    int id = Console.readInt("Digite o id do cliente: ");
                    try {
                        cliente = clienteService.recuperarClientePorId(id);
                        System.out.println(cliente);
                        System.out.println("1. Alterar nome do cliente.");
                        System.out.println("2. Voltar.");
                        int esc = Console.readInt("Entre opção: ");
                        if (esc == 1) {
                            nome = Console.readLine("Digite o novo nome do cliente: ");
                            clienteService.alterar(cliente, nome);
                        }
                    } catch (ObjetoNaoEncontradoException e) { System.out.println(e.getMessage()); }
                } case 6 -> {
                    flag = false;
                    System.out.println("Retornando...");
                }default -> {
                    System.out.println("Opção invalida!");
                }
            }
        }
    }
}
