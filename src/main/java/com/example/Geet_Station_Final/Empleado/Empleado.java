
package com.example.Geet_Station_Final.Empleado;

import com.example.Geet_Station_Final.Puesto.Puesto;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table (name = "empleado")
public class Empleado {
   
    @Id
    private int dni;
    private String nombre;
    private String apellido;
    private String nombrecompleto;
    private int telefono;
    private String usuario;
    private String password;
    
    @ManyToOne()
    @JoinColumn(name = "puesto_idpuesto")
    private Puesto puesto;      
}
