package sv.edu.udb.controller;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sv.edu.udb.model.Producto;
import sv.edu.udb.model.Usuario;
import sv.edu.udb.service.ProductoService;

import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;

    @GetMapping("")
    public String show(Model model){
        model.addAttribute("productos", productoService.findAll());
        return "administrador/productos/show";
    }

    @GetMapping("/create")
    public String create(){
        return "administrador/productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto){
        LOGGER.info("Este es el objeto producto  {}", producto);

        Usuario u = new Usuario(1,"","","","","","","");

        producto.setUsuario(u);

        productoService.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Producto producto = new Producto();

        Optional<Producto> optionalProducto = productoService.get(id);

        producto = optionalProducto.get(); //Obtiene el producto que hemos mandado a buscar

        LOGGER.info("Producto buscado: {}", producto);
        model.addAttribute("producto",producto);

        return "/administrador/productos/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto){
        productoService.update(producto);
        return "redirect:/productos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable  Integer id){
        productoService.delete(id);
        return "redirect:/productos";
    }
}
