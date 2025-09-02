package br.com.internato.repository;

import br.com.internato.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);

    // Busca usuários que possuem um perfil com o ID informado
    List<Usuario> findByPerfil_Id(Long idPerfil);

    // (opcional) já com o objeto Perfil
    // List<Usuario> findByPerfil(Perfil perfil);
}