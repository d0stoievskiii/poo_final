package uff.br.dao;

import uff.br.model.Trecho;
import java.util.List;

public interface TrechoDAO  extends DAOgenerico <Trecho> {
    public List<Trecho> recuperarTrechoPorOrigem(String origem);
    public List<Trecho> recuperarTrechoPorDestino(String destino);
    public Trecho recuperarTrechoPorOrigemDestino(String origem, String destino);
}
