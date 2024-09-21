package uff.br.dao;

import uff.br.model.Cliente;
import java.util.List;

public interface ClienteDAO extends DAOgenerico <Cliente> {
    Cliente buscarClientePorCpf(String Cpf);
}
