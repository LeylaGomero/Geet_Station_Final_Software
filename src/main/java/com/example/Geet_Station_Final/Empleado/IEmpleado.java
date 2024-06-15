
package com.example.Geet_Station_Final.Empleado;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpleado extends CrudRepository<Empleado,Integer> {
    
            @Query(value="SELECT * FROM empleado "
            + "WHERE usuario = ?1 and password = ?2",nativeQuery=true)
             Empleado validar(String usuario, String password);
}
