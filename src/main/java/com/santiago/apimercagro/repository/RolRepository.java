package com.santiago.apimercagro.repository;

import com.santiago.apimercagro.model.Rol;
import com.santiago.apimercagro.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {
    Optional<Rol> findRolByNombre(Roles nombre);
}
