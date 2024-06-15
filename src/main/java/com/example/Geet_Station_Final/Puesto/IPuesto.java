
package com.example.Geet_Station_Final.Puesto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPuesto extends CrudRepository<Puesto,Integer> {
    
}
