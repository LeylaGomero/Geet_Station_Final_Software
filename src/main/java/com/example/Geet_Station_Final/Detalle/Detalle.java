
package com.example.Geet_Station_Final.Detalle;

import com.example.Geet_Station_Final.Producto.Producto;
import com.example.Geet_Station_Final.Proforma.Proforma;
import java.util.List;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="detalle")
public class Detalle {
    
    @EmbeddedId
    private Producto_key_Proforma id;
    
    @ManyToOne
    @MapsId("idproducto")
    @JoinColumn(name="producto_idproducto")
    private Producto producto;
    
    @ManyToOne
    @MapsId("idProforma")
    @JoinColumn(name="proforma_idproforma")
    private Proforma proforma;
    
    private int token;
    private int cantidad;
    
    
}
