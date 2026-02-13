package com.santiago.apimercagro.dto;

import com.santiago.apimercagro.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor

public class RolDTO {
    private Long idRol;
    private Roles nombre;
    private String descripcion;
    private Long cantidadUsuarios;
}
