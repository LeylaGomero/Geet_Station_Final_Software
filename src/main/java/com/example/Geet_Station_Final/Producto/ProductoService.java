package com.example.Geet_Station_Final.Producto;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProducto Data;

    @Override
    public List<Producto> Listar() {
        return (List<Producto>) Data.findAll();
    }

    @Override
    public Optional<Producto> ConsulatarId(int id) {
        return Data.findById(id);
    }

    @Override
    public void Guargar(Producto p) {
        Data.save(p);
    }

    @Override
    public void Eliminar(int id) {
    Data.deleteById(id);
    }

}
