package uff.br.service;

import uff.br.exception.ObjetoNaoEncontradoException;
import uff.br.exception.VooComExecException;
import uff.br.model.Voo;
import uff.br.util.FabricaDeDaos;
import uff.br.dao.VooDAO;

import java.util.List;

public class VooService {
    private final VooDAO vooDAO = FabricaDeDaos.getDAO(VooDAO.class);

    public Voo recuperarVooPorId(int id) {
        Voo voo = vooDAO.recuperarPorId(id);
        if (voo == null) {
            throw new ObjetoNaoEncontradoException("Voo inexistente");
        }
        return voo;
    }

    public Voo incluir(Voo voo) {
        vooDAO.incluir(voo);
        return voo;
    }

    public Voo remover(int id) {
        Voo voo = this.recuperarVooPorId(id);
        if (voo.getExecvoos() == null || voo.getExecvoos().isEmpty()) {
            vooDAO.remover(id);
        } else {
            throw new VooComExecException("Voo com execuções cadastradas não pode ser deletado");
        }
        return voo;
    }

    public List<Voo> recuperarVoos() {
        return vooDAO.recuperarTodos();
    }

    public List<Voo> recuperarVooPorOrigem(String origem) {
        List<Voo> voos = vooDAO.recuperarVooPorOrigem(origem);
        if (voos == null) {
            throw new ObjetoNaoEncontradoException("Voo inexistente");
        }
        return voos;
    }
}
