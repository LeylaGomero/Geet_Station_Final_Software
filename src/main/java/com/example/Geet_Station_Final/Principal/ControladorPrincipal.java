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
        return "Principal";
    }

}
