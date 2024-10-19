package sv.edu.udb.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.model.DetalleOrden;
import sv.edu.udb.model.Orden;
import sv.edu.udb.model.Producto;
import sv.edu.udb.model.Usuario;
import sv.edu.udb.service.IDetalleOrdenService;
import sv.edu.udb.service.IOrdenService;
import sv.edu.udb.service.IProductoService;
import sv.edu.udb.service.IUsuarioService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {
    final private IProductoService service;
    final private IUsuarioService usuarioService;
    final private IOrdenService ordenService;
    final private IDetalleOrdenService detalleOrdenService;
    private Logger logger = LoggerFactory.getLogger(HomeController.class);
    //Almacena los detalles de una orden
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();
    //Almacena datos de la orden
    Orden orden = new Orden();

    @GetMapping("")
    public String home(Model model, HttpSession session){
        logger.info("Session del usuario: {}", session.getAttribute("idUsuario"));
        model.addAttribute("productos", service.findAll());
        //session
        model.addAttribute("sesion", session.getAttribute("idUsuario"));
        return "/usuario/home";
    }

    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable(name = "id")Integer id, Model model, HttpSession session){
        logger.info("Id producto enviado como parametro {}",id);
        Producto producto;
        Optional<Producto> productoOptional = service.get(id);
        producto = productoOptional.get();

        model.addAttribute("producto", producto);
        model.addAttribute("sesion", session.getAttribute("idUsuario"));

        //session
        model.addAttribute("sesion", session.getAttribute("idUsuario"));

        return "usuario/productohome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model, HttpSession session){
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto;
        double sumaTotal = 0;

        Optional<Producto> optionalProducto = service.get(id);
        logger.info("Producto añadido: {}", optionalProducto.get());
        logger.info("Cantidad: {}", cantidad);
        producto = optionalProducto.get();

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio()*cantidad);
        detalleOrden.setProducto(producto);

        //Validacion que un producto no se duplique en el detalle
        Integer idProducto = producto.getId();
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);
        if(!ingresado){
            detalles.add(detalleOrden);
        }

        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();

        orden.setTotal(sumaTotal);

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        //session
        model.addAttribute("sesion", session.getAttribute("idUsuario"));

        return "usuario/carrito";
    }

    //Eliminar articulos del carrito
    @GetMapping("/delete/cart/{id}")
    public String deleteProductCart(@PathVariable(name = "id") Integer id, Model model){
        //Nueva lista de productos
        List<DetalleOrden> ordenesNuevas = new ArrayList<>();

        for(DetalleOrden detalleOrden : detalles){
            if(detalleOrden.getProducto().getId() != id){
                ordenesNuevas.add(detalleOrden);
            }
        }

        //Se asigna nueva lista con productos restantes
        detalles = ordenesNuevas;

        double sumaTotal = 0;
        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();

        orden.setTotal(sumaTotal);

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model model, HttpSession session){
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        //session
        model.addAttribute("sesion", session.getAttribute("idUsuario"));

        return "/usuario/carrito";
    }

    @GetMapping("/order")
    public String order(Model model, HttpSession session){
        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idUsuario").toString())).get();

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", usuario);

        //session
        model.addAttribute("sesion", session.getAttribute("idUsuario"));

        return "/usuario/resumenorden";
    }

    //Guarda la orden
    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session){
        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.generarNumeroOrden());

        //Usuario
        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idUsuario").toString())).get();
        orden.setUsuario(usuario);
        ordenService.save(orden);

        //guardar detalles
        for(DetalleOrden obj : detalles){
            obj.setOrden(orden);
            detalleOrdenService.save(obj);
        }

        //Reiniciar carrito
        orden = new Orden();
        detalles.clear();

        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombre, Model model, HttpSession session){
        logger.info("Nombre del producto: {}", nombre);
        List<Producto> productos = service.findAll().stream()
                .filter(p -> p.getNombre().contains(nombre)).collect(Collectors.toList());
        model.addAttribute("productos", productos);

        //session
        model.addAttribute("sesion", session.getAttribute("idUsuario"));

        return "usuario/home";
    }
}
