
package com.example.Geet_Station_Final.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    
    public List<Producto> Listar();
    public Optional<Producto> ConsulatarId(int id);
    public void Guargar(Producto p);
    public void Eliminar(int id);
    
}
