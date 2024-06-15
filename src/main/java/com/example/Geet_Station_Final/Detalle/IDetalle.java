
package com.example.Geet_Station_Final.Detalle;


import com.example.Geet_Station_Final.Proforma.Proforma;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalle extends CrudRepository<Detalle,Integer>{

        @Query(value="SELECT * FROM detalle "
            + "WHERE proforma_idproforma = ?1",nativeQuery=true)
        List<Detalle> filtrar(Proforma id);
}
