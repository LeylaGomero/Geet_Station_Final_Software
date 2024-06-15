package com.example.Geet_Station_Final.Categoria;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService implements ICategoriaService {
    
    @Autowired
    private ICategoria Data;
    
    @Override
    public List<Categoria> Listar() {
        return (List<Categoria>) Data.findAll();
    }
    
    @Override
    public Optional<Categoria> ConsulatarId(int id) {
        return Data.findById(id);
    }
    
    @Override
    public void Guargar(Categoria c) {
        Data.save(c);
    }
    
    @Override
    public void Eliminar(int id) {
        Data.deleteById(id);
    }
    
}
