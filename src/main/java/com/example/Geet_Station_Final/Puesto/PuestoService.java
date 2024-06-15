package com.example.Geet_Station_Final.Puesto;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PuestoService implements IPuestoService {

    @Autowired
    private IPuesto Data;

    @Override
    public List<Puesto> Listar() {
        return (List<Puesto>) Data.findAll();
    }

    @Override
    public Optional<Puesto> ConsulatarId(int id) {
        return Data.findById(id);
    }

    @Override
    public void Guargar(Puesto p) {
        Data.save(p);
    }

    @Override
    public void Eliminar(int id) {
        Data.deleteById(id);
    }

}
