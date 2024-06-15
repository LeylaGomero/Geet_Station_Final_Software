package com.example.Geet_Station_Final.Categoria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table( name="categoria" )
public class Categoria {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idcategoria;
    private String nombre;
    
}
