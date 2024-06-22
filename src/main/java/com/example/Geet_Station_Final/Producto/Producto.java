package com.example.Geet_Station_Final.Producto;

import com.example.Geet_Station_Final.Categoria.Categoria;
import com.example.Geet_Station_Final.Detalle.Detalle;
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
@Table(name = "producto")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idproducto;
    // Campos que representan las propiedades del producto
    private String nombre;
    private float precio;
    private int stock;
    private String estado;
    private String imagen;

    @ManyToOne()
    @JoinColumn(name = "categoria_idcategoria")
    private Categoria categoria;

    @OneToMany(mappedBy = "producto")
    Set<Detalle> item;
}
