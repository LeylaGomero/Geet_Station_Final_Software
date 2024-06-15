
package com.example.Geet_Station_Final.Cliente;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService{
   
        @Autowired
    private ICliente Data;

    @Override
    public List<Cliente> Listar() {
        return (List<Cliente>) Data.findAll();
    }

    @Override
    public Optional<Cliente> ConsulatarId(int id) {
        return Data.findById(id);
    }

    @Override
    public void Guargar(Cliente p) {
        Data.save(p);
    }

    @Override
    public void Eliminar(int id) {
    Data.deleteById(id);
    }
    
}
