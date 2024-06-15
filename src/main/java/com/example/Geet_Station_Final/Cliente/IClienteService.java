package com.example.Geet_Station_Final.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    public List<Cliente> Listar();
    public Optional<Cliente> ConsulatarId(int id);
    public void Guargar(Cliente p);
    public void Eliminar(int id);

}
