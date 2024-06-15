
package com.example.Geet_Station_Final.Categoria;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoria extends CrudRepository<Categoria,Integer> {
    
}
