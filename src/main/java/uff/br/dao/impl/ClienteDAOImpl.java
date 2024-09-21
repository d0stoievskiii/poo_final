package uff.br.dao.impl;

import uff.br.dao.ClienteDAO;
import uff.br.model.Cliente;

import java.util.List;

public class ClienteDAOImpl extends DAOGenericoImpl<Cliente> implements ClienteDAO {

    public Cliente buscarClientePorCpf(String Cpf) {
        try {
            return map.values().stream().filter((cliente) -> cliente.getCpf().equals(Cpf)).toList().get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
