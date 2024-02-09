package com.entra21.gfarm.repository;


import com.entra21.gfarm.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  UserDetails findByEmail(String email);
}
