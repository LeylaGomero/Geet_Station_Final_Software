package com.example.Geet_Station_Final.Inicio;

import com.example.Geet_Station_Final.Empleado.Empleado;
import com.example.Geet_Station_Final.Empleado.IEmpleadoService;
import com.example.Geet_Station_Final.usuario.IServiceUsuario;
import com.example.Geet_Station_Final.usuario.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControladorIniciarSecion {

    String carpeta = "InicioSesion/";
    int parametro;
    @Autowired
    private IEmpleadoService serviceEmpleado;

    @Autowired
    private IServiceUsuario serviceUsuario;
    

    @GetMapping("/")
    public String inicio(Model model) {

        parametro = parametro + 0;
        model.addAttribute("parametro", parametro);
        return carpeta + "InicioSesion";
    }

    @PostMapping("/validar")
    public String validar(@RequestParam("usuario") String usuario,
            @RequestParam("password") String password,
            Model model) {

        Empleado cuenta = serviceEmpleado.validarSecion(usuario, password);

        Usuario u = new Usuario();

        if (cuenta != null) {
            if (usuario.equals(cuenta.getUsuario()) && password.equals(cuenta.getPassword())) {
                Integer i = 1;
                u.setIdusuario(i);
                u.setEmpleado(cuenta);
                serviceUsuario.Guargar(u);
                parametro = 0;
                model.addAttribute("usuario",u);
                
                return "Principal";
            } else {
                parametro = parametro + 1;
                System.out.println("Credenciales incorrectos");
            }
        } else {
            parametro = parametro + 1;
            System.out.println("Datos o existen");
        }

        return inicio(model);
    }
}
