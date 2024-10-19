package sv.edu.udb.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sv.edu.udb.model.Orden;
import sv.edu.udb.model.Producto;
import sv.edu.udb.service.IOrdenService;
import sv.edu.udb.service.IProductoService;
import sv.edu.udb.service.IUsuarioService;

import java.util.List;

@Controller
@RequestMapping("administrador")
@RequiredArgsConstructor
public class AdministradorController {
    final private IProductoService service;
    final private IUsuarioService usuarioService;
    final private IOrdenService ordenService;

    Logger logger = LoggerFactory.getLogger(AdministradorController.class);

    @GetMapping
    public String home(Model model){
        List<Producto> productos = service.findAll();
        model.addAttribute("productos", productos);
        return "administrador/home";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model){
        model.addAttribute("usuarios", usuarioService.findAll());
        return "administrador/usuarios";
    }

    @GetMapping("/ordenes")
    public String ordenes(Model model){
        model.addAttribute("ordenes", ordenService.findAll());
        return "administrador/ordenes";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable(name = "id") Integer id, Model model){
        logger.info("ID de la orden: {}", id);
        Orden orden = ordenService.findById(id).get();
        model.addAttribute("detalles", orden.getDetalle());
        return "administrador/detalleorden";
    }
}
