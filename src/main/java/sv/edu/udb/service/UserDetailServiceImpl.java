/*package sv.edu.udb.service;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sv.edu.udb.model.Usuario;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
   //Inyeccoin  de clase IUsuarioService
    @Autowired
    private IUsuarioService usuarioService;

    //Creacion de objeto para clave encriptada

    private BCryptPasswordEncoder bCrypt;

    //Objeto paea guardar la sesion del usuario
    @Autowired
    HttpSession session;

    //Objeto para imprimir en consola
    private Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    //Metodo implementado por UserDenailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user by username: " + username);
        Optional<Usuario> optionalUsuario = usuarioService.findbyEmail(username);
        if (optionalUsuario.isPresent()) {
            if (optionalUsuario.isPresent()){
                log.info("Loading user by username:  {}", optionalUsuario.get());
                session.setAttribute("idusuario", optionalUsuario.get().getId());
                Usuario usuario = optionalUsuario.get();
                return User.builder().username(usuario.getNombre()).password(bCrypt.encode(usuario.getUsername())).roles(usuario.getTipo()).build();
            }else {
                throw new UsernameNotFoundException("Usuario no encontrado");
            }
        }
        return null;
    }
}*/
