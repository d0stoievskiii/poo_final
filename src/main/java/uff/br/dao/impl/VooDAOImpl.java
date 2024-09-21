package uff.br.dao.impl;

import uff.br.dao.VooDAO;
import uff.br.model.Trecho;
import uff.br.model.Voo;

import java.util.LinkedList;
import java.util.List;

public class VooDAOImpl extends DAOGenericoImpl<Voo> implements VooDAO {
    public List<Voo> recuperarVooPorOrigem(String origem) {
        return map.values().stream().filter((voo)-> voo.getOrigem().equals(origem)).toList();
    }
    public List<Voo> recuperarVooPorDestino(String destino) {
        return map.values().stream().filter((voo)-> voo.getDestino().equals(destino)).toList();
    }
    public List<Voo> recuperarVoosComTrecho(Integer id) {
        LinkedList<Voo> resp = new LinkedList<>();
        for (Voo voo : map.values()) {
            for (Trecho trecho : voo.getTrechos()) {
                if (trecho.getId() == id) {
                    resp.add(voo);
                    break;
                }
            }
        }
        return resp;
    }
}
