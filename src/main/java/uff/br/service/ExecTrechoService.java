package uff.br.service;

import uff.br.exception.ObjetoNaoEncontradoException;
import uff.br.model.ExecTrecho;
import uff.br.dao.ExecTrechoDAO;
import uff.br.model.ExecVoo;
import uff.br.model.Passagem;
import uff.br.model.Trecho;
import uff.br.util.FabricaDeDaos;

import java.util.Calendar;
import java.util.List;

public class ExecTrechoService {
    private final ExecTrechoDAO execTrechoDAO = FabricaDeDaos.getDAO(ExecTrechoDAO.class);

    public ExecTrecho recuperarExecTrechoPorId(int id) {
        ExecTrecho execTrecho = execTrechoDAO.recuperarPorId(id);
        if (execTrecho == null) {
            throw new ObjetoNaoEncontradoException("ExecTrecho não encontrado");
        }
        return execTrecho;
    }

    public ExecTrecho incluir(ExecTrecho execTrecho) {
        execTrechoDAO.incluir(execTrecho);
        execTrecho.getTrecho().getExecTrechos().add(execTrecho);
        return execTrecho;
    }

    public ExecTrecho remover(int id) {
        ExecTrecho execTrecho = recuperarExecTrechoPorId(id);
        execTrecho.getTrecho().getExecTrechos().remove(execTrecho);
        execTrechoDAO.remover(id);
        return execTrecho;
    }

    public List<ExecTrecho> recuperarExecTrechos() {
        return execTrechoDAO.recuperarTodos();
    }

    public List<ExecTrecho> recuperarExecPorTrechoParaVenda(Trecho trecho, Calendar c) {
        List<ExecTrecho> execTrechos = execTrechoDAO.recuperarTrechoPorOrigemDestino(trecho.getOrigem(), trecho.getDestino());
        //System.out.println("tam: " + trecho.getExecTrechos().size());
        //System.out.println("tam: " + execTrechos.size());
        //System.out.println(trecho);
        return execTrechos.stream().filter((et) -> et.getInicio().compareTo(c) >= 0).sorted((et1, et2) -> et1.getInicio().compareTo(et2.getInicio())).toList();
    }
}
