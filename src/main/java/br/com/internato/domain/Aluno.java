package br.com.internato.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "alunos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluno")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String matricula;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private Boolean ativo;

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    /* se quiser preencher automaticamente */
    @PrePersist
    void prePersist() {
        criadoEm = LocalDateTime.now();
    }
}