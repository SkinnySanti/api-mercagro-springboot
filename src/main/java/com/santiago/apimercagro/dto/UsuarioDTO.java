package com.santiago.apimercagro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor

public class UsuarioDTO{
    private Long idUsuario;
    private String username;
    private String email;
    private String nombreCompleto;
    private List<String> nombreRoles;
}
