package sv.edu.udb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.udb.model.DetalleOrden;
import sv.edu.udb.repository.IDetalleOrdenRepository;

@Service
@RequiredArgsConstructor
public class DetalleOrdenServiceImpl implements IDetalleOrdenService{
    final private IDetalleOrdenRepository detalleOrdenRepository;

    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        return detalleOrdenRepository.save(detalleOrden);
    }
}
