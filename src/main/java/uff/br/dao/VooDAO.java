package uff.br.dao;

import uff.br.model.Voo;

import java.util.List;

public interface VooDAO  extends DAOgenerico<Voo> {
    public List<Voo> recuperarVoosComTrecho(Integer id);
    public List<Voo> recuperarVooPorOrigem(String origem);
    public List<Voo> recuperarVooPorDestino(String destino);
}
