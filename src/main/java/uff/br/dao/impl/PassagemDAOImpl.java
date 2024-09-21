package uff.br.dao.impl;

import uff.br.dao.DAOgenerico;
import uff.br.dao.PassagemDAO;
import uff.br.model.Passagem;

import java.util.List;

public class PassagemDAOImpl extends DAOGenericoImpl<Passagem> implements PassagemDAO {
    public List<Passagem> recuperarPassagemPorCpf(String cpf) {
        return map.values().stream().filter((passagem) -> passagem.getCliente().getCpf().equals(cpf)).toList();
    }
}
