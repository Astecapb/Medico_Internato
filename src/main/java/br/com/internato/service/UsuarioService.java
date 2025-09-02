package br.com.internato.service;

import br.com.internato.domain.Usuario;
import br.com.internato.repository.UsuarioRepository;
import br.com.internato.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    // Criar ou atualizar
    public Usuario salvar(Usuario usuario) {
        if (repository.existsByEmail(usuario.getEmail())) {
            throw new BusinessException("E-mail já cadastrado");
        }
        return repository.save(usuario);
    }

    // Lista geral
    public List<Usuario> listar() {
        return repository.findAll();
    }

    // Por ID
    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));
    }

    // Lista por perfil (COORDENADOR, PRECEPTOR, ALUNO, SECRETARIA)
    // Lista por perfil via ID do perfil
    public List<Usuario> listarPorPerfil(Long idPerfil) {
    return repository.findByPerfil_Id(idPerfil);
    }

    // Buscar por e-mail
    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("E-mail não encontrado"));
    }

    // Excluir
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("Usuário não existe");
        }
        repository.deleteById(id);
    }
}