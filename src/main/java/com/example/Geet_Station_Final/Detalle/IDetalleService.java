
package com.example.Geet_Station_Final.Detalle;


import com.example.Geet_Station_Final.Proforma.Proforma;
import java.util.List;
import java.util.Optional;

public interface IDetalleService {
    
    public List<Detalle> Listar();
    public Optional<Detalle> ConsulatarId(int id);
    public void Guargar(Detalle d);
    public void Eliminar(int id);
    public List<Detalle> filtrarDetalles(Proforma id);
}
