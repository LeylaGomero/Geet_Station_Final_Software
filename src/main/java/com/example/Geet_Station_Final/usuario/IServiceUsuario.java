
package com.example.Geet_Station_Final.usuario;

import java.util.List;
import java.util.Optional;

public interface IServiceUsuario {
   
    public List<Usuario> Listar();
    public Optional<Usuario> ConsulatarId(int id);
    public void Guargar(Usuario u);
    public void Eliminar(int id);
    
    
}
