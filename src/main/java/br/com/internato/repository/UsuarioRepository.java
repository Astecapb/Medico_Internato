package br.com.internato.repository;

import br.com.internato.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);        // ← novo
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByPerfil(Usuario.Perfil perfil);
}