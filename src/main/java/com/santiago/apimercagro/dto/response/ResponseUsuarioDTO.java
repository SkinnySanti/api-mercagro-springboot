package com.santiago.apimercagro.dto.response;

import com.santiago.apimercagro.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
@AllArgsConstructor

public class ResponseUsuarioDTO{
    private Long idUsuario;
    private String username;
    private String email;
    private String nombreCompleto;
    private Roles nombreRol;
}
