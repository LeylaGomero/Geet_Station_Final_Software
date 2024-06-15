package com.example.Geet_Station_Final.Ventas;

import com.example.Geet_Station_Final.Detalle.Detalle;
import com.example.Geet_Station_Final.Detalle.IDetalleService;
import com.example.Geet_Station_Final.Proforma.IProformaService;
import com.example.Geet_Station_Final.Proforma.Proforma;
import com.example.Geet_Station_Final.usuario.IServiceUsuario;
import com.example.Geet_Station_Final.usuario.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/ventas/")
@Controller
public class ControladorVentas {

    String carpeta = "Venta/";

    @Autowired
    private IProformaService serviceProforma;

    @Autowired
    private IDetalleService serviceDetalle;

    @Autowired
    private IServiceUsuario serviceUsuario;

    @GetMapping("/")
    public String listar(Model model) {

        List<Proforma> proformas = serviceProforma.Listar();

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
        model.addAttribute("proformas", proformas);
        return carpeta + "Venta";
        
    }

    @GetMapping("/detalle")
    public String detalle(@RequestParam(value = "id", required = false) Proforma id, Model model) {

        List<Detalle> detalles = serviceDetalle.filtrarDetalles(id);

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

        System.out.println("dato: " + id.getIdProforma());
        model.addAttribute("detalles", detalles);
        return carpeta + "detalle";
        //return carpeta + "eliminar";
    }

    @GetMapping("/pago")
    public String pago(@RequestParam(value = "id", required = false) Proforma id, Model model) {

        id.setCancelado(true);
        serviceProforma.Guargar(id);

        return listar(model);
    }
}
