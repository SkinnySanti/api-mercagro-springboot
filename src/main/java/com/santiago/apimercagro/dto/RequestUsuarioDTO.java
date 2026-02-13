package com.santiago.apimercagro.dto;

import com.santiago.apimercagro.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor

public class RequestUsuarioDTO{
    private String username;
    private String password;
    private String email;
    private String nombreCompleto;
    private Roles nombreRol;
}
