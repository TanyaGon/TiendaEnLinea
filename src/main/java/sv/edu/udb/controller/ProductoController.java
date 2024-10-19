package sv.edu.udb.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sv.edu.udb.model.Producto;
import sv.edu.udb.model.Usuario;
import sv.edu.udb.service.IProductoService;
import sv.edu.udb.service.IUsuarioService;
import sv.edu.udb.service.UploadFileService;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {
    final private Logger logger = Logger.getLogger("ProductoController.class");

    final private IProductoService service;

    final private IUsuarioService usuarioService;

    final private UploadFileService fileService;

    @GetMapping("")
    public String show(Model model){
        model.addAttribute("productos", service.findAll());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create(){
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto, @RequestParam(name = "img") MultipartFile file, HttpSession session) throws IOException {
        logger.info("Este es el objeto producto " + producto.toString());
        Usuario user = usuarioService.findById(Integer.parseInt(session.getAttribute("idUsuario").toString())).get();
        producto.setUsuario(user);

        //imagen
        if(producto.getId() == null){   //->Cuando se crea un producto
            String nombreImagen = fileService.saveImage(file);
            producto.setImagen(nombreImagen);
        }

        service.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") Integer id, Model model){
        Producto producto;
        Optional<Producto> optionalProducto = service.get(id);
        producto = optionalProducto.get();

        model.addAttribute("producto", producto);
        return "productos/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto, @RequestParam(name = "img") MultipartFile file) throws IOException {
        Producto p;
        p = service.get(producto.getId()).get();

        if(file.isEmpty()){ //->Se edita el producto pero no se cambia la imagen
            producto.setImagen(p.getImagen());
        }else{//    Cuando se edita tambien la imagen
            //Elimina imagen cuando no sea la imagen por defecto
            if(!p.getImagen().equals("default.jpg")){
                fileService.deleteImage(p.getImagen());
            }
            String nombreImagen = fileService.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        producto.setUsuario(p.getUsuario());
        service.update(producto);
        return "redirect:/productos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id")Integer id){
        Producto p;
        p = service.get(id).get();

        //Elimina imagen cuando no sea la imagen por defecto
        if(!p.getImagen().equals("default.jpg")){
            fileService.deleteImage(p.getImagen());
        }

        service.delete(id);
        return "redirect:/productos";
    }
}
