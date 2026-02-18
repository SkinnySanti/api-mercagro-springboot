package com.santiago.apimercagro.service;

import com.santiago.apimercagro.dto.request.RequestCultivoDTO;
import com.santiago.apimercagro.dto.response.ResponseCultivoDTO;
import com.santiago.apimercagro.exception.NotFoundException;
import com.santiago.apimercagro.mapper.CultivoMapper;
import com.santiago.apimercagro.model.Cultivo;
import com.santiago.apimercagro.enums.EstadoCultivo;
import com.santiago.apimercagro.model.Producto;
import com.santiago.apimercagro.repository.CultivoRepository;
import com.santiago.apimercagro.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CultivoService implements ICultivoService{


    private final CultivoRepository cultivoRepository;
    private final ProductoRepository productoRepository;
    private final CultivoMapper cultivoMapper;

    public CultivoService(CultivoRepository cultivoRepository, ProductoRepository productoRepository,
                          CultivoMapper cultivoMapper) {
        this.cultivoRepository = cultivoRepository;
        this.productoRepository = productoRepository;
        this.cultivoMapper = cultivoMapper;
    }

    @Override
    public List<ResponseCultivoDTO> listarCultivos() {
        return cultivoMapper.toDtoList(cultivoRepository.findAll());
    }

    @Override
    public ResponseCultivoDTO buscarCultivoPorId(Long id) {
        Cultivo cultivoEncontrado = cultivoRepository.findById(id).orElseThrow(()->
                new NotFoundException("Cultivo no encontrado"));

        return cultivoMapper.toDto(cultivoEncontrado);
    }

    @Override
    public ResponseCultivoDTO crearCultivo(RequestCultivoDTO requestCultivoDTO) {
        //Verificaiones para creacion del cultivo
        if(requestCultivoDTO == null) throw new RuntimeException("CultivoDto es null");
        //Verificacion para cultivos PUBLICADOS
        if(requestCultivoDTO.getEstado().equals(EstadoCultivo.PUBLICADO)){
            //Buscamos si existe el producto por nombre
            Producto producto = productoRepository.findProductoByNombre(requestCultivoDTO.getNombreProducto())
                    .orElseThrow(()-> new NotFoundException("Producto no asigando para el cultivo PUBLICADO " + requestCultivoDTO.getNombreProducto()));
            //Validacion para ver si tiene un precio el cultivo
            if(requestCultivoDTO.getPrecio() == null) throw new RuntimeException("El precio es obligatorio para publicar el cultivo");
            //Validadcion para verificar si el precio del cultivo es mayor al del precio del producto
            if(requestCultivoDTO.getPrecio().compareTo(producto.getPrecio()) == 0 || requestCultivoDTO.getPrecio().compareTo(producto.getPrecio()) > 0)
                throw new RuntimeException("El precio debe estar por debajo del precio de mercado: " + producto.getPrecio());
        }

        Cultivo cultivo = cultivoMapper.toEntity(requestCultivoDTO);

        return cultivoMapper.toDto(cultivoRepository.save(cultivo));
    }

    @Override
    public ResponseCultivoDTO actualizarCultivo(Long id, RequestCultivoDTO requestCultivoDTO) {
        Cultivo cultivo = cultivoRepository.findById(id).orElseThrow(()->
                new NotFoundException("Cultivo no encontrado"));
        Producto producto = productoRepository.findProductoByNombre(requestCultivoDTO.getNombreProducto())
                        .orElseThrow(()->new NotFoundException("Producto no encontrado"
                                + requestCultivoDTO.getNombreProducto()));

        cultivo.setNombre(requestCultivoDTO.getNombreCultivo());
        cultivo.setProducto(producto);
        cultivo.setDescripcion(requestCultivoDTO.getDescripcion());
        cultivo.setCantidad(requestCultivoDTO.getCantidad());
        cultivo.setUnidadMedida(requestCultivoDTO.getUnidadMedida());
        cultivo.setPrecio(requestCultivoDTO.getPrecio());
        cultivo.setEstadoCultivo(requestCultivoDTO.getEstado());
        cultivo.setFechaCreacion(requestCultivoDTO.getFechaCreacion());

        return cultivoMapper.toDto(cultivoRepository.save(cultivo));
    }

    @Override
    public void eliminarCultivo(Long id) {
        if(!cultivoRepository.existsById(id)){
            throw new NotFoundException("Cultivo no encontrado: " + id);
        }
        cultivoRepository.deleteById(id);
    }
}
