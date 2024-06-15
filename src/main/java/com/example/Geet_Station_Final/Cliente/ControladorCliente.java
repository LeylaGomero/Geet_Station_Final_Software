package com.example.Geet_Station_Final.Cliente;

import com.example.Geet_Station_Final.Proforma.IProformaService;
import com.example.Geet_Station_Final.Proforma.Proforma;
import com.example.Geet_Station_Final.usuario.IServiceUsuario;
import com.example.Geet_Station_Final.usuario.Usuario;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/cliente/")
@Controller
public class ControladorCliente {

    @Autowired
    private IClienteService serviceCliente;

    @Autowired
    private IServiceUsuario serviceUsuario;
    
    @Autowired
    private IProformaService serviceProforma;

    String carpeta = "Cliente/";
    
    int estado;

    @GetMapping("/")
    public String Listar(Model model) {

        Cliente c = new Cliente();
        c.setApellido("");
        c.setDni(0);
        c.setNombre("");

        estado = estado+1;
        List<Cliente> clientes = serviceCliente.Listar();

        Usuario usuario = new Usuario();
        List<Usuario> Listausuario = serviceUsuario.Listar();
        for (int x = 0; x < Listausuario.size(); x++) {
            if (Listausuario.get(x).getIdusuario() == 1) {
                usuario.setIdusuario(Listausuario.get(x).getIdusuario());
                usuario.setEmpleado(Listausuario.get(x).getEmpleado());
                break;
            }
        }

        System.out.println("enviando id: " + usuario.getIdusuario());
        System.out.println("enviando nombre: " + usuario.getEmpleado().getNombrecompleto());
        model.addAttribute("usuario", usuario);

        model.addAttribute("estado", estado);
        model.addAttribute("c", c);
        model.addAttribute("clientes", clientes);
        return carpeta + "Cliente";
    }

    @PostMapping("/BuscarReniec")
    public String BuscarReniec(@RequestParam("dni") String dni,
            Model model) {

        Cliente c = new Cliente();

        String direccion = "https://dniruc.apisperu.com/api/v1/dni/";
        String token = "?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImJlcGEueDEwMHByZUBnbWFpbC5jb20ifQ.8-wBhLQc-kOnp1WgqghYV0Mv0XUzuMY2karFFkdsDwI";
        String enlace = direccion + dni + token;
        try {
            URL url = new URL(enlace);
            URLConnection req = url.openConnection();
            req.connect();
            com.google.gson.JsonParser jp = new com.google.gson.JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) req.getContent()));

            if (root.isJsonObject()) {

                JsonObject rootobj = root.getAsJsonObject();
                if (!rootobj.isJsonNull()) {

                    c.setNombre(rootobj.get("nombres").getAsString().trim());
                    c.setApellido(rootobj.get("apellidoPaterno").getAsString().trim() + " " + rootobj.get("apellidoMaterno").getAsString().trim());
                    c.setDni(Integer.parseInt(rootobj.get("dni").getAsString().trim()));

                }

            }
        } catch (Exception e) {

            System.out.println("error:" + e.getMessage());
        }

        List<Cliente> clientes = serviceCliente.Listar();

        Usuario usuario = new Usuario();
        List<Usuario> Listausuario = serviceUsuario.Listar();
        for (int x = 0; x < Listausuario.size(); x++) {
            if (Listausuario.get(x).getIdusuario() == 1) {
                usuario.setIdusuario(Listausuario.get(x).getIdusuario());
                usuario.setEmpleado(Listausuario.get(x).getEmpleado());
                break;
            }
        }

        System.out.println("enviando id: " + usuario.getIdusuario());
        System.out.println("enviando nombre: " + usuario.getEmpleado().getNombrecompleto());
        model.addAttribute("usuario", usuario);

        model.addAttribute("c", c);
        model.addAttribute("clientes", clientes);
        return carpeta + "Cliente";
    }

    @PostMapping("/Agregar")
    public String Agregar(@RequestParam("nombre") String nombre,
            @RequestParam("dni") int dni,
            @RequestParam("apellido") String apellido,
            @RequestParam("telefono") int telefono,
            @RequestParam("correo") String correo,
            Model model) {

        boolean bandera = false;
        List<Cliente> clientes = serviceCliente.Listar();

        for (int f = 0; f < clientes.size(); f++) {
            if (clientes.get(f).getDni() == dni) {
                estado = 1;
                bandera = true;
            }

        }

        if (!bandera) {

            estado = 0;
            Cliente c = new Cliente();

            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setNombrecompleto(nombre + " " + apellido);
            c.setDni(dni);
            c.setTelefono(telefono);
            c.setCorreo(correo);

            serviceCliente.Guargar(c);

        }

        return Listar(model);
    }
    /*No se usará el metodo eliminar debido a la regla del negocio, pero si se desea en un futuro implementar, se podrá usar*/
    @GetMapping("/Eliminar")
    public String Eliminar(@RequestParam("dni") int dni,
            Model model) {

        List<Proforma> proformas = serviceProforma.Listar();
        boolean bandera = false;
        for(int g=0;g<proformas.size();g++)
        {
            if(proformas.get(g).getEmpleado().getDni() == dni)
            {
                bandera = true;
            }
                
        }
        
        if(!bandera)
        {
         serviceCliente.Eliminar(dni);  
        }
              
        return Listar(model);
    }

    @PostMapping("/actualizar")
    public String Actualizar(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("telefono") int telefono,
            @RequestParam("dni") int dni,
            @RequestParam("correo") String correo,
            Model model) {
        Cliente c = new Cliente();

        c.setNombre(nombre);
        c.setApellido(apellido);
        c.setNombrecompleto(nombre + " " + apellido);
        c.setDni(dni);
        c.setTelefono(telefono);
        c.setCorreo(correo);

        serviceCliente.Guargar(c);

        return Listar(model);
    }
}
