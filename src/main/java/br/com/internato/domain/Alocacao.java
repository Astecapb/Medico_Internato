package br.com.internato.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alocacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Alocacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_plantao")
    private Plantao plantao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_aluno")
    private Usuario aluno;

    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    private Integer horasReais; // horas efetivamente cumpridas

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StatusAlocacao status = StatusAlocacao.PENDENTE;

    public enum StatusAlocacao {
        PENDENTE, CONFIRMADO, FALTA, REPOSICAO
    }
}