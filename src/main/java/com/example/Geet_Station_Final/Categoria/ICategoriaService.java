package com.example.Geet_Station_Final.Categoria;

import java.util.List;
import java.util.Optional;

public interface ICategoriaService {

    public List<Categoria> Listar();
    public Optional<Categoria> ConsulatarId(int id);
    public void Guargar(Categoria c);
    public void Eliminar(int id);

}
