package com.santiago.apimercagro.service;

import com.santiago.apimercagro.dto.request.RequestUsuarioDTO;
import com.santiago.apimercagro.dto.response.ResponseUsuarioDTO;

import java.util.List;

public interface IUsuarioService {
    List<ResponseUsuarioDTO> listarUsuarios();
    ResponseUsuarioDTO BuscarUsuarioPorUsername(String username);
    ResponseUsuarioDTO BuscarUsuarioPorId(Long id);
    ResponseUsuarioDTO crearUsuario(RequestUsuarioDTO usuarioDTO);
    ResponseUsuarioDTO actualizarUsuario(Long id, RequestUsuarioDTO usuarioDTO);
    void eliminarUsuario(Long id);

}
