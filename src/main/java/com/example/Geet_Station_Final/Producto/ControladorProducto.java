package com.example.Geet_Station_Final.Producto;

import com.example.Geet_Station_Final.Categoria.Categoria;
import com.example.Geet_Station_Final.Categoria.ICategoriaService;
import com.example.Geet_Station_Final.usuario.IServiceUsuario;
import com.example.Geet_Station_Final.usuario.Usuario;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/producto/")
@Controller
public class ControladorProducto {
String carpeta = "Producto/";

    @Autowired
    private IProductoService serviceProducto;

    @Autowired
    private ICategoriaService serviceCategoria;

        @Autowired
    private IServiceUsuario serviceUsuario;
    
    @GetMapping("/")
    public String Listar(Model model) {

        List<Producto> productos = serviceProducto.Listar();
        List<Categoria> categorias = serviceCategoria.Listar();
        
                Usuario usuario =new Usuario();
        List<Usuario> Listausuario = serviceUsuario.Listar();
        for(int x=0;x<Listausuario.size();x++)
        {
            if(Listausuario.get(x).getIdusuario() == 1)
            {
                usuario.setIdusuario(Listausuario.get(x).getIdusuario());
                usuario.setEmpleado(Listausuario.get(x).getEmpleado());
                break;
            }
        }
      
        System.out.println("enviando id: " + usuario.getIdusuario());
        System.out.println("enviando nombre: " + usuario.getEmpleado().getNombrecompleto());
        model.addAttribute("usuario",usuario);
        

        model.addAttribute("categorias", categorias);
        model.addAttribute("productos", productos);
        return carpeta + "Producto";
    }

    @PostMapping("/Agregar")
    public String Agregar(
            @RequestParam("nombre") String nombre,
            @RequestParam("categoria") Categoria categoria,
            @RequestParam("precio") float precio,
            @RequestParam("stock") int stock,
            @RequestParam("estado") String estado,
            @RequestParam("imagen") String imagen,
            Model model) 
    {
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setPrecio(precio);
        p.setStock(stock);
        p.setEstado(estado);
        p.setCategoria(categoria);
        p.setImagen(imagen);
        serviceProducto.Guargar(p);

        return Listar(model);
    }

    @GetMapping("/Eliminar")
    public String Eliminar(@RequestParam("id") int id,
            Model model) {

        serviceProducto.Eliminar(id);
        
        return Listar(model);
    }

    @PostMapping("/actualizar")
    public String Actualizar(@RequestParam("id") int id,
            @RequestParam("nombre") String nombre,
            @RequestParam("categoria") Categoria categoria,
            @RequestParam("precio") float precio,
            @RequestParam("stock") int stock,
            @RequestParam("estado") String estado,
            @RequestParam("imagen") String imagen,
            Model model) {

        Producto p = new Producto();
        p.setIdproducto(id);
        p.setNombre(nombre);
        p.setStock(stock);
        p.setPrecio(precio);
        p.setEstado(estado);
        p.setCategoria(categoria);
        p.setImagen(imagen);
      

        serviceProducto.Guargar(p);

        return Listar(model);
    }
}
