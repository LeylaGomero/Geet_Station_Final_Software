package com.example.Geet_Station_Final.Principal;

import com.example.Geet_Station_Final.Categoria.Categoria;
import com.example.Geet_Station_Final.Categoria.ICategoriaService;
import com.example.Geet_Station_Final.Producto.IProductoService;
import com.example.Geet_Station_Final.Producto.Producto;
import com.example.Geet_Station_Final.usuario.IServiceUsuario;
import com.example.Geet_Station_Final.usuario.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/principal/")
@Controller
public class ControladorPrincipal {

    @Autowired
    private IProductoService serviceProducto;

    @Autowired
    private ICategoriaService serviceCategoria;
    
    @Autowired
    private IServiceUsuario serviceUsuario;

    @GetMapping("/")
    public String Listar(Model model) {
        // Obtiene la lista de productos desde el servicio
        List<Producto> productos = serviceProducto.Listar();
        // Obtiene la lista de categorías desde el servicio
        List<Categoria> categorias = serviceCategoria.Listar();
        Usuario usuario =new Usuario();
        // Obtiene la lista de usuarios desde el servicio
        List<Usuario> Listausuario = serviceUsuario.Listar();
        // Recorre la lista de usuarios para encontrar el usuario con ID 1
        for(int x=0;x<Listausuario.size();x++)
        {
            if(Listausuario.get(x).getIdusuario() == 1)
            {
                // Establece los detalles del usuario encontrado en la instancia de usuario
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
        return "Principal";
    }

}
