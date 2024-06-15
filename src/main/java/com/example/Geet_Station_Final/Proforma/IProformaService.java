package com.example.Geet_Station_Final.Proforma;

import java.util.List;
import java.util.Optional;

public interface IProformaService {

    public List<Proforma> Listar();
    public Optional<Proforma> ConsultarId(int id);
    public void Guargar(Proforma p);
    public void Eliminar(int id);

}
