package com.santiago.apimercagro.mapper;

import com.santiago.apimercagro.dto.RolDTO;
import com.santiago.apimercagro.model.Rol;
import com.santiago.apimercagro.model.Roles;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class RolMapper {
    public static RolDTO toDTO (Rol rol){
        if(rol == null) return null;
        //Validación del rol
        Roles nombre;
        try{
            nombre = rol.getNombre();
        }catch (IllegalArgumentException e){
            throw new RuntimeException("Rol invalido: " + rol.getNombre() +
                    ". Roles validos: " + Arrays.toString(Roles.values()));
        }

        //Calculo cantidad de usuarios X rol
        Long cantidadUsuarios = (long) rol.getUsuarios().size();


        return RolDTO.builder()
                .idRol(rol.getIdRol())
                .nombre(nombre)
                .descripcion(rol.getDescripcion())
                .cantidadUsuarios(cantidadUsuarios)
                .build();
    }

    public static Rol toEntity (RolDTO rolDTO){
        if (rolDTO == null) return null;

        Roles nombre;

        try {
            nombre = rolDTO.getNombre();
        }catch (IllegalArgumentException e){
            throw new RuntimeException("Rol invalido: " + rolDTO.getNombre() +
                    ". Roles validos: " + Arrays.toString(Roles.values()));
        }

        return Rol.builder()
                .idRol(rolDTO.getIdRol())
                .nombre(nombre)
                .descripcion(rolDTO.getDescripcion())
                .usuarios(Collections.emptyList())
                .build();
    }
}
