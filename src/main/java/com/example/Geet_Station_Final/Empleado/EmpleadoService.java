
package com.example.Geet_Station_Final.Empleado;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService implements IEmpleadoService {

    @Autowired
    private IEmpleado Data;
    
    @Override
    public List<Empleado> Listar() {
    return (List<Empleado>) Data.findAll();
    }

    @Override
    public Optional<Empleado> ConsulatarId(int id) {
    return Data.findById(id);
    }

    @Override
    public void Guargar(Empleado e) {
    Data.save(e);
    }

    @Override
    public void Eliminar(int id) {
    Data.deleteById(id);
    }

    @Override
    public Empleado validarSecion(String usuario, String password) {
       
        return Data.validar(usuario, password);
    }
    
}
