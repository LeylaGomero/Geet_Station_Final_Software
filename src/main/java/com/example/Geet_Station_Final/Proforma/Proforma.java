package com.example.Geet_Station_Final.Proforma;

import com.example.Geet_Station_Final.Cliente.Cliente;
import com.example.Geet_Station_Final.Detalle.Detalle;
import com.example.Geet_Station_Final.Empleado.Empleado;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "proforma")
public class Proforma implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idProforma;
    private String fecha;
    private float total;
    private boolean cancelado;
  
    
    
    @ManyToOne()
    @JoinColumn(name ="cliente_idcliente")
    private Cliente cliente;
    
        @ManyToOne()
    @JoinColumn(name ="empleado_idempleado")
    private Empleado empleado;
    
    @OneToMany(mappedBy = "proforma")
    Set<Detalle> item;

}
