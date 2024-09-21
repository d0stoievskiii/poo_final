package uff.br.dao.impl;

import uff.br.dao.TrechoDAO;
import uff.br.model.Trecho;

import java.util.List;

public class TrechoDAOImpl extends DAOGenericoImpl<Trecho> implements TrechoDAO {
    public List<Trecho> recuperarTrechoPorOrigem(String origem) {
        return map.values().stream().filter((trecho)-> trecho.getOrigem().equals(origem)).toList();
    }
    public List<Trecho> recuperarTrechoPorDestino(String destino) {
        return map.values().stream().filter((trecho)-> trecho.getDestino().equals(destino)).toList();
    }
    public Trecho recuperarTrechoPorOrigemDestino(String origem, String destino) {
        List<Trecho> candidatos = recuperarTrechoPorOrigem(origem);
        for(Trecho trecho: candidatos) {
            if(trecho.getDestino().equals(destino)) {
                return trecho;
            }
        }
        return null;
    }

}
