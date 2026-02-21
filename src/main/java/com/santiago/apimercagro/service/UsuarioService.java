package com.santiago.apimercagro.service;

import com.santiago.apimercagro.dto.request.RequestUsuarioDTO;
import com.santiago.apimercagro.dto.response.ResponseUsuarioDTO;
import com.santiago.apimercagro.exception.NotFoundException;
import com.santiago.apimercagro.mapper.UsuarioMapper;
import com.santiago.apimercagro.model.Rol;
import com.santiago.apimercagro.model.Usuario;
import com.santiago.apimercagro.repository.RolRepository;
import com.santiago.apimercagro.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService{


    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;


    public UsuarioService(UsuarioMapper usuarioMapper, UsuarioRepository usuarioRepository,
                          RolRepository rolRepository) {
        this.usuarioMapper = usuarioMapper;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public List<ResponseUsuarioDTO> listarUsuarios() {
        return usuarioMapper.toDtoList(usuarioRepository.findAll());
    }

    @Override
    public ResponseUsuarioDTO BuscarUsuarioPorUsername(String username) {
        Usuario usuario = usuarioRepository.findUsuarioByUsername(username)
                .orElseThrow(()-> new NotFoundException("Usuario no encontrado: " + username));

        return usuarioMapper.toDto(usuario);
    }

    @Override
    public ResponseUsuarioDTO BuscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()->
                new NotFoundException("Usuario no encontrado"));
        return usuarioMapper.toDto(usuario);
    }

    @Override
    public ResponseUsuarioDTO crearUsuario(RequestUsuarioDTO usuarioDTO) {
        Rol rol = rolRepository.findRolByNombre(usuarioDTO.getNombreRol())
                .orElseThrow(()-> new NotFoundException("Rol no encontrado"));
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO, rol);
        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    @Override
    public ResponseUsuarioDTO actualizarUsuario(Long id, RequestUsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()->
                new NotFoundException("Usuario no encontrado"));

        Rol rol = rolRepository.findRolByNombre(usuarioDTO.getNombreRol()).orElseThrow(()->
                new NotFoundException("Rol no encontrado"));

        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setNombreCompleto(usuarioDTO.getNombreCompleto());
        usuario.setRol(rol);

        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    @Override
    public void eliminarUsuario(Long id) {
        if(!usuarioRepository.existsById(id)){
            throw new NotFoundException("Usuario no encontrado para eliminar");
        }
        usuarioRepository.deleteById(id);
    }
}
