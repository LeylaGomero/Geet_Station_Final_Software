package com.example.Geet_Station_Final.Empleado;

import com.example.Geet_Station_Final.Proforma.IProformaService;
import com.example.Geet_Station_Final.Proforma.Proforma;
import com.example.Geet_Station_Final.Puesto.IPuestoService;
import com.example.Geet_Station_Final.Puesto.Puesto;
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

@RequestMapping("/empleado/")
@Controller
public class ControladorEmpleado {

    @Autowired
    private IEmpleadoService serviceEmpleado;

    @Autowired
    private IPuestoService servicePuesto;

    @Autowired
    private IServiceUsuario serviceUsuario;
    
    @Autowired
    private IProformaService serviceProforma;

    String carpeta = "Empleado/";

    @GetMapping("/")
    public String Listar(Model model) {

        Empleado em = new Empleado();

        em.setDni(0);
        em.setNombre("");
        em.setApellido("");

        List<Empleado> empleados = serviceEmpleado.Listar();
        List<Puesto> puestos = servicePuesto.Listar();

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
        model.addAttribute("puestos", puestos);
        model.addAttribute("e", em);
        model.addAttribute("empleados", empleados);
        return carpeta + "Empleado";
    }

    @PostMapping("/BuscarReniec")
    public String BuscarReniec(@RequestParam("dni") String dni,
            Model model) {

        Empleado em = new Empleado();

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

                    em.setNombre(rootobj.get("nombres").getAsString().trim());
                    em.setApellido(rootobj.get("apellidoPaterno").getAsString().trim() + " " + rootobj.get("apellidoMaterno").getAsString().trim());
                    em.setDni(Integer.parseInt(rootobj.get("dni").getAsString().trim()));

                }

            }
        } catch (Exception e) {

            System.out.println("error:" + e.getMessage());
        }

        List<Empleado> empleados = serviceEmpleado.Listar();
        List<Puesto> puestos = servicePuesto.Listar();

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
        model.addAttribute("puestos", puestos);
        model.addAttribute("e", em);
        model.addAttribute("empleados", empleados);
        return carpeta + "Empleado";
    }

    @PostMapping("/Agregar")
    public String Agregar(
            @RequestParam("nombre") String nombre,
            @RequestParam("dni") int dni,
            @RequestParam("puesto") Puesto puesto,
            @RequestParam("apellido") String apellido,
            @RequestParam("usuario") String usuario,
            @RequestParam("password") String password,
            @RequestParam("telefono") int telefono,
            Model model) {
        
        boolean bandera = false;
        List<Empleado> empleados = serviceEmpleado.Listar();
        for(int f=0;f<empleados.size();f++)
        {
            if(empleados.get(f).getDni() == dni)
            {
                bandera = true;
            }
                
        }
        
        if(!bandera)
        {
         Empleado e = new Empleado();

        e.setNombre(nombre);
        e.setApellido(apellido);
        e.setNombrecompleto(nombre + " " + apellido);
        e.setDni(dni);
        e.setTelefono(telefono);
        e.setPuesto(puesto);
        e.setUsuario(usuario);
        e.setPassword(password);

        serviceEmpleado.Guargar(e);   
        }

        return Listar(model);
    }

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
        
        List<Usuario> Listausuario = serviceUsuario.Listar();
        
        for(int t=0;t<Listausuario.size();t++)
        {
            if(Listausuario.get(t).getEmpleado().getDni() == dni)
            {
                bandera = true;
            }
        }
        
        if(!bandera)
        {
          serviceEmpleado.Eliminar(dni);  
        }
        
        return Listar(model);
    }

    @PostMapping("/actualizar")
    public String Actualizar(@RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("telefono") int telefono,
            @RequestParam("puesto") Puesto puesto,
            @RequestParam("dni") int dni,
            @RequestParam("usuario") String usuario,
            @RequestParam("password") String password,
            Model model) {
        Empleado e = new Empleado();

        e.setNombre(nombre);
        e.setApellido(apellido);
        e.setNombrecompleto(nombre + " " + apellido);
        e.setDni(dni);
        e.setTelefono(telefono);
        e.setPuesto(puesto);
        e.setUsuario(usuario);
        e.setPassword(password);

        serviceEmpleado.Guargar(e);

        return Listar(model);
    }

}
