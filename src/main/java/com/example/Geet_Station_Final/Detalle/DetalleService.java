
package com.example.Geet_Station_Final.Detalle;

import com.example.Geet_Station_Final.Proforma.Proforma;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DetalleService implements IDetalleService{
  
    @Autowired
    private IDetalle Data;

    @Override
    public List<Detalle> Listar() {
        return (List<Detalle>) Data.findAll();
    }

    @Override
    public Optional<Detalle> ConsulatarId(int id) {
        return Data.findById(id);
    }

    @Override
    public void Guargar(Detalle d) {
        Data.save(d);
    }

    @Override
    public void Eliminar(int id) {
    Data.deleteById(id);
    }

    @Override
    public List<Detalle> filtrarDetalles(Proforma id) {
        return Data.filtrar(id);
    }

    
}
