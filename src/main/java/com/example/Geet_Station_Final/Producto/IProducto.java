
package com.example.Geet_Station_Final.Producto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IProducto extends CrudRepository<Producto,Integer> {
    
}
