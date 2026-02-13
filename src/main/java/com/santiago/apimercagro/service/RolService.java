package com.santiago.apimercagro.service;

import com.santiago.apimercagro.dto.RolDTO;
import com.santiago.apimercagro.exception.NotFoundException;
import com.santiago.apimercagro.mapper.RolMapper;
import com.santiago.apimercagro.model.Rol;
import com.santiago.apimercagro.enums.Roles;
import com.santiago.apimercagro.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService implements IRolService{


    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public List<RolDTO> listarRoles() {
        return rolRepository.findAll().stream()
                .map(RolMapper::toDTO)
                .toList();
    }

    @Override
    public RolDTO buscarRolPorNombre(Roles nombre) {
        Rol rolEncontrado = rolRepository.findRolByNombre(nombre).orElseThrow(()->
                new NotFoundException("Rol no encontrado: " + nombre.toString()));
        return RolMapper.toDTO(rolEncontrado);
    }

    @Override
    public RolDTO buscarRolPorId(Long id) {
        Rol rolEncontrado = rolRepository.findById(id).orElseThrow(()->
                new NotFoundException("Rol no encontrado"));

        return RolMapper.toDTO(rolEncontrado);
    }

    @Override
    public RolDTO crearRol(RolDTO rolDTO) {
        Rol rol = RolMapper.toEntity(rolDTO);
        return RolMapper.toDTO(rolRepository.save(rol));
    }

    @Override
    public RolDTO actualizarRol(Long id, RolDTO rolDTO) {
        Rol rolEncontrado = rolRepository.findById(id).orElseThrow(()->
                new NotFoundException("Rol no encontrado"));

        rolEncontrado.setNombre(rolDTO.getNombre());
        rolEncontrado.setDescripcion(rolDTO.getDescripcion());

        return RolMapper.toDTO(rolRepository.save(rolEncontrado));
    }

    @Override
    public void eliminarRol(Long id) {
        if(!rolRepository.existsById(id)){
            throw new NotFoundException("Rol no encontrado para eliminar");
        }

        rolRepository.deleteById(id);
    }
}
