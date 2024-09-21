package uff.br.service;

import uff.br.exception.ObjetoJaCadastradoException;
import uff.br.exception.ObjetoNaoEncontradoException;
import uff.br.exception.ClienteComPassagensExeception;
import uff.br.model.Cliente;
import uff.br.dao.ClienteDAO;
import uff.br.util.FabricaDeDaos;

import java.util.List;

public class ClienteService {
    private final ClienteDAO clienteDAO = FabricaDeDaos.getDAO(ClienteDAO.class);


    public Cliente recuperarClientePorId(int id) {
        Cliente cliente = clienteDAO.recuperarPorId(id);
        if (cliente == null) {
            throw new ObjetoNaoEncontradoException("Cliente inexistente");
        }
        return cliente;
    }

    public Cliente incluir(Cliente cliente) {
        try {
            this.recuperarClientePorCpf(cliente.getCpf());
            throw new ObjetoJaCadastradoException("Cliente com esse cpf já existe.");
        } catch (ObjetoNaoEncontradoException e) {
            clienteDAO.incluir(cliente);
            return cliente;
        }
    }

    public Cliente remover(int id) {
        Cliente cliente = this.recuperarClientePorId(id);
        if (cliente.getPassagens() == null || cliente.getPassagens().isEmpty()) {
            clienteDAO.remover(id);
        } else {
            throw new ClienteComPassagensExeception("Cliente com passagens não pode ser removido.");
        }
        return cliente;
    }

    public Cliente alterar(Cliente cliente, String nome) {
        cliente.setNome(nome);
        return cliente;
    }

    public List<Cliente> recuperarClientes() {
        return clienteDAO.recuperarTodos();
    }

    public Cliente recuperarClientePorCpf(String cpf) {
        Cliente cliente = clienteDAO.buscarClientePorCpf(cpf);
        if (cliente == null) {
            throw new ObjetoNaoEncontradoException("Cliente inexistente");
        }
        return cliente;
    }
}
