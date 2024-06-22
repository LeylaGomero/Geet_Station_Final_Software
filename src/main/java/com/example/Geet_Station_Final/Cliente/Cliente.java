package com.example.Geet_Station_Final.Cliente;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

//Es un comentario de prueba que no debería afectar en el uso del código, de lo contrario me destierran del grupo

@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    private int dni;
    private String nombre;
    private String apellido;
    private String nombrecompleto;
    private int telefono;
    private String correo;

}
