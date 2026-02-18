package com.santiago.apimercagro.mapper;

import com.santiago.apimercagro.dto.request.RequestUsuarioDTO;
import com.santiago.apimercagro.dto.response.ResponseUsuarioDTO;
import com.santiago.apimercagro.exception.NotFoundException;
import com.santiago.apimercagro.model.Rol;
import com.santiago.apimercagro.model.Usuario;
import com.santiago.apimercagro.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {


    private final RolRepository rolRepository;

    public ResponseUsuarioDTO toDto(Usuario usuario){
        if(usuario == null) return null;

        return ResponseUsuarioDTO.builder()
                .idUsuario(usuario.getIdUsuario())
                .username(usuario.getUsername())
                .email(usuario.getEmail())
                .nombreCompleto(usuario.getNombreCompleto())
                .nombreRol(usuario.getRol().getNombre())
                .build();
    }

    public Usuario toEntity(RequestUsuarioDTO usuarioDTO){
        if (usuarioDTO == null) return null;

        Rol rol = rolRepository.findRolByNombre(usuarioDTO.getNombreRol())
                .orElseThrow(()-> new NotFoundException("Rol no encontrado"));

        return Usuario.builder()
                .username(usuarioDTO.getUsername())
                .password(usuarioDTO.getPassword())
                .email(usuarioDTO.getEmail())
                .nombreCompleto(usuarioDTO.getNombreCompleto())
                .rol(rol)
                .build();
    }

    public List<ResponseUsuarioDTO> toDtoList (List<Usuario> usuarios){
        if(usuarios == null || usuarios.isEmpty()) return Collections.emptyList();

        return usuarios.stream()
                .map(this::toDto)
                .toList();
    }
}
