package uff.br.service;

import uff.br.dao.PassagemDAO;
import uff.br.exception.ObjetoNaoEncontradoException;
import uff.br.model.Passagem;
import uff.br.util.FabricaDeDaos;

import java.util.List;

public class PassagemService {
    private final PassagemDAO passagemDAO = FabricaDeDaos.getDAO(PassagemDAO.class);


    public Passagem incluir(Passagem passagem) {
        for(int i = 0; i < passagem.getEtrechos().size(); i++) {
            passagem.getEtrechos().get(i).getPassagens().add(passagem);
        }
        passagemDAO.incluir(passagem);
        passagem.getCliente().getPassagens().add(passagem);
        return passagem;
    }

    public Passagem remover(int id) {
        Passagem passagem = this.recuperarPassagemPorId(id);
        passagem.getCliente().getPassagens().remove(passagem);
        passagemDAO.remover(id);
        return passagem;
    }

    public Passagem recuperarPassagemPorId(int id) {
        Passagem passagem = passagemDAO.recuperarPorId(id);
        if (passagem == null) {
            throw new ObjetoNaoEncontradoException("Passagem inexistente");
        }
        return passagem;
    }

    public List<Passagem> recuperarPassagens() {
        return passagemDAO.recuperarTodos();
    }
    
}
