package br.com.internato.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.internato.domain.Alocacao;


public interface AlocacaoRepository extends JpaRepository<Alocacao, Long> {

    long countByPlantaoId(Long id_plantao);

    @Query("""
        SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END
        FROM Alocacao a
        WHERE a.aluno.id = :id_aluno
          AND (a.plantao.dataHoraInicio < :fim AND a.plantao.dataHoraFim > :inicio)
    """)
    boolean existsByAlunoIdAndHorario(@Param("id_aluno") Long id_aluno,
                                      @Param("inicio") LocalDateTime inicio,
                                      @Param("fim") LocalDateTime fim);

    List<Alocacao> findByAlunoId(Long id_aluno);
}