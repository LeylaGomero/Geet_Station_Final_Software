
package com.example.Geet_Station_Final.Detalle;

import com.example.Geet_Station_Final.Producto.Producto;
import com.example.Geet_Station_Final.Proforma.Proforma;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Producto_key_Proforma  implements Serializable{
    
    @Column(name="producto_idproducto")
    private int idproducto;
    
    @Column(name="proforma_idproforma")
    private int idproforma;

    public Producto_key_Proforma(Producto idproducto, Proforma idproforma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Producto_key_Proforma(int idproducto, int idproforma) {
        this.idproducto = idproducto;
        this.idproforma = idproforma;
    }

    public Producto_key_Proforma() {
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdproforma() {
        return idproforma;
    }

    public void setIdproforma(int idproforma) {
        this.idproforma = idproforma;
    }
    
    
    
    
}
