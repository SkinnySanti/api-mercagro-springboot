package com.santiago.apimercagro.repository;

import com.santiago.apimercagro.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findUsuarioByUsername(String username);
}
