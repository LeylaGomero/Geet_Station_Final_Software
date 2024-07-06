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

/*Este comentario sirve para delimitar desde donde se está agregando hasta donde queda*/
@GetMapping("/recuperar-contrasena")
    public String mostrarRecuperarContrasena() {
        return carpeta + "RecuperarContrasena"; // Crear una vista para recuperación de contraseña
    }

    @PostMapping("/recuperar-contrasena")
    public String recuperarContrasena(@RequestParam("email") String email, Model model) {
        Empleado empleado = serviceEmpleado.findByEmail(email);
        if (empleado != null) {
            // Lógica para enviar correo electrónico con la contraseña o un enlace para restablecerla.
            // Esto es solo un ejemplo y deberías implementar un servicio de correo real.
            String nuevaContrasena = "nuevaContrasenaGenerada"; // Generar una nueva contraseña o un token
            empleado.setPassword(nuevaContrasena);
            serviceEmpleado.guardar(empleado);
            model.addAttribute("mensaje", "Se ha enviado un correo electrónico con las instrucciones para restablecer su contraseña.");
        } else {
            model.addAttribute("error", "El correo electrónico no está registrado.");
        }
        return carpeta + "RecuperarContrasena";
    }
/*Fin */

    
}
