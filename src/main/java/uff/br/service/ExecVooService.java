package uff.br.service;

import uff.br.exception.ObjetoNaoEncontradoException;
import uff.br.model.ExecVoo;
import uff.br.dao.ExecVooDAO;
import uff.br.model.Voo;
import uff.br.util.FabricaDeDaos;

import java.util.Calendar;
import java.util.List;

public class ExecVooService {

    private final ExecVooDAO execVooDAO = FabricaDeDaos.getDAO(ExecVooDAO.class);

    public ExecVoo incluir(ExecVoo execVoo) {
        execVooDAO.incluir(execVoo);
        execVoo.getVoo().getExecvoos().add(execVoo);
        return execVoo;
    }

    public ExecVoo recuperarExecVooPorId(int id) {
        ExecVoo execVoo = execVooDAO.recuperarPorId(id);
        if (execVoo == null) {
            throw new ObjetoNaoEncontradoException("ExecVoo nao encontrado");
        }
        return execVoo;
    }

    public ExecVoo remover(int id) {
        ExecVoo execVoo = recuperarExecVooPorId(id);
        execVoo.getVoo().getExecvoos().remove(execVoo);
        execVooDAO.remover(id);
        return execVoo;
    }

    public List<ExecVoo> recuperarExecVoos() {
        return execVooDAO.recuperarTodos();
    }

    public ExecVoo recuperarExecPorVooData(Voo voo, int ano, int mes, int dia) {
        List<ExecVoo> evoos = voo.getExecvoos();
        evoos = evoos.stream().filter((ev) -> ev.getInicio().get(Calendar.YEAR) == ano).toList();
        evoos = evoos.stream().filter((ev) -> ev.getInicio().get(Calendar.MONTH) == (mes-1)).toList();
        evoos = evoos.stream().filter((ev) -> ev.getInicio().get(Calendar.DAY_OF_MONTH) == dia).toList();
        if (evoos.isEmpty()) {
            throw new ObjetoNaoEncontradoException("Exec Voo não existe para esta data.");
        }
        return evoos.get(0);
    }
}
