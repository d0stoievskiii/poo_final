package uff.br.dao;

import uff.br.model.Passagem;
import java.util.List;

public interface PassagemDAO extends DAOgenerico<Passagem> {
    public List<Passagem> recuperarPassagemPorCpf(String cpf);
}
