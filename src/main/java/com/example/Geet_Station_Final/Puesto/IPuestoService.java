
package com.example.Geet_Station_Final.Puesto;

import java.util.List;
import java.util.Optional;

public interface IPuestoService {
    
    
    public List<Puesto> Listar();
    public Optional<Puesto> ConsulatarId(int id);
    public void Guargar(Puesto p);
    public void Eliminar(int id);
    
}
