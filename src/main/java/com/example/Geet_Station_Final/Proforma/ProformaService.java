package com.example.Geet_Station_Final.Proforma;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProformaService implements IProformaService {

    @Autowired
    private IProforma Data;

    @Override
    public List<Proforma> Listar() {
        return (List<Proforma>) Data.findAll();
    }

    @Override
    public void Guargar(Proforma p) {
        Data.save(p);
    }

    @Override
    public void Eliminar(int id) {
        Data.deleteById(id);
    }

    @Override
    public Optional<Proforma> ConsultarId(int id) {
        

        return Data.findById(id);
    }

}
