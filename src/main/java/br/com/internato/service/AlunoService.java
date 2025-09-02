package br.com.internato.service;   // linha corrigida

import br.com.internato.domain.Aluno;
import br.com.internato.repository.AlunoRepository;
import br.com.internato.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository repository;

    // Criar ou atualizar
    public Aluno salvar(Aluno aluno) {
    if (repository.existsByMatricula(aluno.getMatricula())) {
        throw new BusinessException("Matrícula já cadastrada");
    }
    return repository.save(aluno);
}

    // Lista geral
    public List<Aluno> listar() {
        return repository.findAll();
    }

    // Por ID
    public Aluno buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException("Aluno não encontrado"));
    }

    // Excluir
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("Aluno não existe");
        }
        repository.deleteById(id);
    }
}