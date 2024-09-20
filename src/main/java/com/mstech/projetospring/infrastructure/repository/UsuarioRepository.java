package com.mstech.projetospring.infrastructure.repository;

import com.mstech.projetospring.infrastructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    boolean existsByEmail(String email);
}
