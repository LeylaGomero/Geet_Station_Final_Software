
package com.example.Geet_Station_Final.Cliente;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICliente extends CrudRepository<Cliente,Integer>{
    
}
