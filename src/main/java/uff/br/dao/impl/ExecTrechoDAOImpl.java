package uff.br.dao.impl;

import uff.br.dao.ExecTrechoDAO;
import uff.br.model.ExecTrecho;
import uff.br.model.Trecho;

import java.util.ArrayList;
import java.util.List;

public class ExecTrechoDAOImpl extends DAOGenericoImpl<ExecTrecho> implements ExecTrechoDAO {
    public List<ExecTrecho> recuperarExecPorTrecho(Trecho t) {
        return map.values().stream().filter((et) -> et.getTrecho().getId() == t.getId()).toList();
    }

    public List<ExecTrecho> recuperarTrechoPorOrigem(String origem) {
        return map.values().stream().filter((et)-> et.getTrecho().getOrigem().equals(origem)).toList();
    }

    public List<ExecTrecho> recuperarTrechoPorOrigemDestino(String origem, String destino) {
        List<ExecTrecho> candidatos = recuperarTrechoPorOrigem(origem);
        List<ExecTrecho> resp = new ArrayList<ExecTrecho>();
        for(ExecTrecho etrecho: candidatos) {
            if(etrecho.getTrecho().getDestino().equals(destino)) {
                resp.add(etrecho);
            }
        }
        return resp;
    }
}
