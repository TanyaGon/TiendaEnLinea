package sv.edu.udb.controller;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sv.edu.udb.model.Producto;
import sv.edu.udb.model.Usuario;
import sv.edu.udb.service.ProductoService;
import sv.edu.udb.service.UploadFileService;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UploadFileService upload;

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
    public String save(Producto producto,@RequestParam("img") MultipartFile file) throws IOException {
        LOGGER.info("Este es el objeto producto  {}", producto);

        Usuario u = new Usuario(1,"","","","","","","");

        producto.setUsuario(u);

        //Imagen
        if (producto.getId()==null){ // cuando se crea un producto
           String nombreImagen = upload.saveImage(file);

           producto.setImagen(nombreImagen);
        }
        else{
            if (file.isEmpty()){ // editamos el producto pero no cambiamos la imagen
                Producto p = new Producto();

                p=productoService.get(producto.getId()).get();

                producto.setImagen(p.getImagen());
            }
            else{
                String nombreImagen = upload.saveImage(file);

                producto.setImagen(nombreImagen);
            }
        }



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
