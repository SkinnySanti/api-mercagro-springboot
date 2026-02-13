package com.santiago.apimercagro.service;

import com.santiago.apimercagro.dto.RolDTO;
import com.santiago.apimercagro.model.Roles;

import java.util.List;

public interface IRolService {
    List<RolDTO> listarRoles();
    RolDTO buscarRolPorNombre(Roles nombre);
    RolDTO buscarRolPorId(Long id);
    RolDTO crearRol(RolDTO rolDTO);
    RolDTO actualizarRol(Long id, RolDTO rolDTO);
    void eliminarRol(Long id);
}
