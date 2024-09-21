package uff.br.service;

import uff.br.dao.VooDAO;
import uff.br.exception.ObjetoNaoEncontradoException;
import uff.br.exception.TrechoComExecException;
import uff.br.exception.TrechoComVooException;
import uff.br.model.Trecho;
import uff.br.dao.TrechoDAO;
import uff.br.model.Voo;
import uff.br.util.FabricaDeDaos;

import java.util.List;

public class TrechoService {
    private final TrechoDAO trechoDAO = FabricaDeDaos.getDAO(TrechoDAO.class);
    private final VooDAO vooDAO = FabricaDeDaos.getDAO(VooDAO.class);

    public Trecho recuperarTrechoPorId(int id) {
        Trecho trecho = trechoDAO.recuperarPorId(id);
        if (trecho == null) {
            throw new ObjetoNaoEncontradoException("Trecho não cadastrado");
        }
        return trecho;
    }

    public Trecho incluir(Trecho trecho) {
        trechoDAO.incluir(trecho);
        return trecho;
    }

    public Trecho remover(int id) {
        Trecho trecho = recuperarTrechoPorId(id);
        List<Voo> voos = vooDAO.recuperarVoosComTrecho(id);
        if (voos.size() > 0) {
            throw new TrechoComVooException("Um voo depende desse trecho!");
        }
        if (trecho.getExecTrechos() == null || trecho.getExecTrechos().isEmpty()) {
            trechoDAO.remover(id);
        } else {
            throw new TrechoComExecException("Trecho com execuções não pode ser removido.");
        }
        return trecho;
    }

    public Trecho recuperarTrechoPorOrigemDestino(String origem, String destino) {
        Trecho trecho = trechoDAO.recuperarTrechoPorOrigemDestino(origem, destino);
        if (trecho == null) {
            throw new ObjetoNaoEncontradoException("Trecho não existe.");
        }
        return trecho;
    }

    public List<Trecho> recuperarTrechosPorOrigem(String origem) {
        List<Trecho> trechos = trechoDAO.recuperarTrechoPorOrigem(origem);
        if (trechos.isEmpty()) {
            throw new ObjetoNaoEncontradoException("Nenhum trecho com origem: " + origem);
        }
        return trechos;
    }

    public List<Trecho> recuperarTrechosPorDestino(String destino) {
        List<Trecho> trechos = trechoDAO.recuperarTrechoPorDestino(destino);
        if (trechos.isEmpty()) {
            throw new ObjetoNaoEncontradoException("Nenhum trecho com destino: " + destino);
        }
        return trechos;
    }

    public List<Trecho> recuperarTrechos() {
        return trechoDAO.recuperarTodos();
    }
}
