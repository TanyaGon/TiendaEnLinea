package sv.edu.udb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sv.edu.udb.model.Usuario;
import sv.edu.udb.repository.UsuarioRepository;

@SpringBootTest
public class UsuarioRepositoryTest {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void insertUser() {
        String nombre = "Mario";
        String username = "mario";
        String email = "mario03@email.com";
        String direccion = "Africano";
        String telefono = "5687-2543";
        String tipo = "Cliente";
        String password = "1234";
        Usuario usuario = Usuario
                .builder()
                    .nombre(nombre)
                    .username(username)
                    .email(email)
                    .direccion(direccion)
                    .telefono(telefono)
                    .tipo(tipo)
                    .password(password)
                .build();
        usuarioRepository.save(usuario);
        System.out.println("Usuario insertado correctamente");
    }
    @Test
    void findUserById(){
        int id = 2;
        Usuario usuario = usuarioRepository.findById(id);
        System.out.println("Usuario encontrado");
        System.out.println(usuario.toString());
    }

    /*@Test
    void update() {
        int id = 1;
        String nombre = "Jose";
        String username = "jose05";
        String email = "jose05@email.com";
        String direccion = "Africano";
        String telefono = "5687-2543";
        String tipo = "Cliente";
        String password = "1234";
        Usuario usuario = Usuario
                .builder()
                .nombre(nombre)
                .username(username)
                .email(email)
                .direccion(direccion)
                .telefono(telefono)
                .tipo(tipo)
                .password(password)
                .build();
        usuarioRepository.update(usuario);
        System.out.println("Usuario actualizado correctamente");
        Usuario usuario2 = usuarioRepository.findById(id);
        System.out.println(usuario2.toString());
    }
    @Test
    void delete(){
        int id = 2;
        Usuario usuario = Usuario
                .builder()
                    .id(id)
                .build();
        usuarioRepository.delete(usuario);
        System.out.println("Usuario eliminado");
    }*/


}