package br.com.internato.repository;

import br.com.internato.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    boolean existsByMatricula(String matricula);   // coluna real

    Optional<Aluno> findByMatricula(String matricula); // se quiser buscar por matrícula
}