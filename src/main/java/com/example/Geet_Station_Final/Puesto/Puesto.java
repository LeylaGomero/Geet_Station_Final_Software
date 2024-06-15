package com.example.Geet_Station_Final.Puesto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="puesto")
public class Puesto {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idpuesto;
    private String nombre;
}
