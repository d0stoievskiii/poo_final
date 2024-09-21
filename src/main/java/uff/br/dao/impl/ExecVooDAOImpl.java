package uff.br.dao.impl;

import uff.br.dao.ExecVooDAO;
import uff.br.model.ExecVoo;

import java.util.List;

public class ExecVooDAOImpl extends DAOGenericoImpl<ExecVoo> implements ExecVooDAO {
    public List<ExecVoo> recuperarExecPorVoo(Integer idVoo) {
        return map.values().stream().filter((ev) -> ev.getVoo().getId() == idVoo).toList();
    }
}
