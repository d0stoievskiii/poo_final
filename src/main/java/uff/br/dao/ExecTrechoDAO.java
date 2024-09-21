package uff.br.dao;

import uff.br.model.ExecTrecho;
import uff.br.model.Trecho;

import java.util.List;

public interface ExecTrechoDAO extends DAOgenerico<ExecTrecho>{
    public List<ExecTrecho> recuperarExecPorTrecho(Trecho t);
    public List<ExecTrecho> recuperarTrechoPorOrigem(String origem);
    public List<ExecTrecho> recuperarTrechoPorOrigemDestino(String origem, String destino);
}
