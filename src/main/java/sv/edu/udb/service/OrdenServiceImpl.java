package sv.edu.udb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.udb.model.Orden;
import sv.edu.udb.model.Usuario;
import sv.edu.udb.repository.IOrdenRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdenServiceImpl implements IOrdenService{
    final private IOrdenRepository ordenRepository;

    @Override
    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }

    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }

    //Formato 0000010
    public String generarNumeroOrden(){
        int numero = 0;
        String numeroConcatenado = "";

        List<Orden> ordenes = findAll();
        List<Integer> numeros = new ArrayList<>();
        ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));

        if(ordenes.isEmpty()){
            numero = 1;
        } else {
            numero = numeros.stream().max(Integer::compare).get();
            numero++;
        }

        if(numero < 10){    //0000000001
            numeroConcatenado = "000000000"+String.valueOf(numero);
        } else if (numero < 100) {
            numeroConcatenado = "00000000"+String.valueOf(numero);
        } else if (numero < 1000) {
            numeroConcatenado = "0000000"+String.valueOf(numero);
        }else if (numero < 10000) {
            numeroConcatenado = "000000"+String.valueOf(numero);
        }

        return numeroConcatenado;
    }

    @Override
    public List<Orden> findByUsuario(Usuario usuario) {
        return ordenRepository.findByUsuario(usuario);
    }

    @Override
    public Optional<Orden> findById(Integer id) {
        return ordenRepository.findById(id);
    }
}
