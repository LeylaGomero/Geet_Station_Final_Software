package com.example.Geet_Station_Final.Proforma;

import com.example.Geet_Station_Final.Cliente.Cliente;
import com.example.Geet_Station_Final.Cliente.IClienteService;
import com.example.Geet_Station_Final.Detalle.Detalle;
import com.example.Geet_Station_Final.Detalle.IDetalleService;
import com.example.Geet_Station_Final.Detalle.Producto_key_Proforma;
import com.example.Geet_Station_Final.Empleado.Empleado;
import com.example.Geet_Station_Final.Producto.IProductoService;
import com.example.Geet_Station_Final.Producto.Producto;
import com.example.Geet_Station_Final.usuario.IServiceUsuario;
import com.example.Geet_Station_Final.usuario.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/proforma/")
@Controller
public class ControladorProforma {

    String carpeta = "Proforma/";
    int estado;
    float CostoTotal;

    @Autowired
    private IClienteService serviceCliente;

    @Autowired
    private IProductoService serviceProducto;

    @Autowired
    private IDetalleService serviceDetalle;

    @Autowired
    private IProformaService serviceProforma;

    @Autowired
    private IServiceUsuario serviceUsuario;

    private ArrayList<Detalle> ListaDetalle = new ArrayList();

    @GetMapping("/")
    public String Listar(Model model) {

        estado = estado + 0;
        CostoTotal = TotalDetalle();

        List<Cliente> clientes = serviceCliente.Listar();
        List<Detalle> detalles = serviceDetalle.Listar();
        List<Producto> productos = serviceProducto.Listar();

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

        model.addAttribute("CostoTotal", CostoTotal);
        model.addAttribute("estado", estado);
        model.addAttribute("detalles", detalles);
        model.addAttribute("clientes", clientes);
        model.addAttribute("ListaDetalle", ListaDetalle);
        model.addAttribute("productos", productos);

        return carpeta + "Proforma";
    }

    @PostMapping("/CrearProforma")
    public String CrearProforma(@RequestParam(value = "idProducto", required = false) Producto idproducto,
            @RequestParam(value = "cantidad", required = false) int cantidad,
            Model model) {

        boolean bandera = false;

        for (int x = 0; x < ListaDetalle.size(); x++) {
            if (ListaDetalle.get(x).getProducto().getIdproducto() == idproducto.getIdproducto()) {

                estado = 1;
                bandera = true;
            }

        }

        if (!bandera && cantidad > idproducto.getStock()) {

            estado = 2;
            bandera = true;
        }

        if (!bandera) {
            estado = 0;
            Detalle d = new Detalle();
            d.setProducto(idproducto);
            d.setCantidad(cantidad);

            ListaDetalle.add(d);
        }

        return Listar(model);
    }

    @PostMapping("/EliminarDetalle")
    public String EliminarDetalle(@RequestParam("codProducto") int id, Model model) {
        estado = 0;
        for (int x = 0; x < ListaDetalle.size(); x++) {
            if (ListaDetalle.get(x).getProducto().getIdproducto() == id) {

                ListaDetalle.remove(x);
                break;
            }
        }

        return Listar(model);
    }

    public void CargarDetalles(Proforma proforma) {

        Detalle d = new Detalle();
        int restar = 0;
        for (int x = 0; x < ListaDetalle.size(); x++) {

            Producto_key_Proforma key = new Producto_key_Proforma(ListaDetalle.get(x).getProducto().getIdproducto(), proforma.getIdProforma());
            d.setId(key);
            d.setProducto(ListaDetalle.get(x).getProducto());
            d.setProforma(proforma);
            d.setCantidad(ListaDetalle.get(x).getCantidad());
            int nuevostok = (ListaDetalle.get(x).getProducto().getStock()) - (ListaDetalle.get(x).getCantidad());
            ListaDetalle.get(x).getProducto().setStock(nuevostok);

            serviceProducto.Guargar(ListaDetalle.get(x).getProducto());
            serviceDetalle.Guargar(d);
        }
    }

    public float TotalDetalle() {

        float total = 0;
        float toalproducto = 0;

        for (int x = 0; x < ListaDetalle.size(); x++) {
            toalproducto = ListaDetalle.get(x).getProducto().getPrecio() * ListaDetalle.get(x).getCantidad();
            total = total + toalproducto;
        }

        return total;
    }

    @PostMapping("/GuardarProforma")
    public String GuardarProforma(@RequestParam("dni") Cliente dni,
            @RequestParam("idusuario") Empleado idusuario,
            Model model) {

        estado = 0;
        boolean bandera = false;
        List<Cliente> clientes = serviceCliente.Listar();

        for (int c = 0; c < clientes.size(); c++) {
            if (clientes.get(c).getDni() == dni.getDni()) {
                bandera = true;
                break;
            }
        }

        if (ListaDetalle.size() > 0 & bandera) {
            Proforma proforma = new Proforma();
            proforma.setFecha((LocalDate.now()).toString());
            proforma.setCliente(dni);
            proforma.setCancelado(false);
            proforma.setEmpleado(idusuario);
            proforma.setTotal(TotalDetalle());
            serviceProforma.Guargar(proforma);

            CargarDetalles(proforma);

            System.out.println("Proceso Exitoso");
            ListaDetalle.clear();
        }

        return Listar(model);
    }

}
