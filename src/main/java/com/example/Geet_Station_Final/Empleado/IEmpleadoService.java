package com.example.Geet_Station_Final.Empleado;

import java.util.List;
import java.util.Optional;

public interface IEmpleadoService {

    public List<Empleado> Listar();

    public Optional<Empleado> ConsulatarId(int id);

    public void Guargar(Empleado e);

    public void Eliminar(int id);
    
    public Empleado validarSecion(String usuario, String password);
}
