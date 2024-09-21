package uff.br.dao;

import uff.br.model.ExecVoo;
import java.util.List;

public interface ExecVooDAO extends DAOgenerico<ExecVoo>{
    public List<ExecVoo> recuperarExecPorVoo(Integer idVoo);
}
