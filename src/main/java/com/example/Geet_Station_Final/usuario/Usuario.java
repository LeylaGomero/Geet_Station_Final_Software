
package com.example.Geet_Station_Final.usuario;

import com.example.Geet_Station_Final.Empleado.Empleado;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

  
@Data
@Entity
@Table(name = "usuario")
public class Usuario {


    @Id
    private int idusuario;
    
    @ManyToOne()
    @JoinColumn(name = "empleado_idempleado")
    private Empleado empleado;
    
}
