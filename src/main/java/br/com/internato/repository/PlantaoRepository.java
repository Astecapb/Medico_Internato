package br.com.internato.repository;

import br.com.internato.domain.Plantao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlantaoRepository extends JpaRepository<Plantao, Long> {

    List<Plantao> findBySemestreId(Long semestreId);
    List<Plantao> findByPreceptor_Id(Long preceptorId);
    List<Plantao> findByAluno_Id(Long alunoId);
    //List<Plantao> findAll(Long semestreId);
    

}
