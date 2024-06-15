
package com.example.Geet_Station_Final.usuario;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUsuario implements IServiceUsuario{
    
   @Autowired
    private IUsuario Data;

    @Override
    public List<Usuario> Listar() {
        return (List<Usuario>) Data.findAll();
    }

    @Override
    public Optional<Usuario> ConsulatarId(int id) {
        return Data.findById(id);
    }

    @Override
    public void Guargar(Usuario u) {
        Data.save(u);
    }

    @Override
    public void Eliminar(int id) {
    Data.deleteById(id);
    }
    
}
