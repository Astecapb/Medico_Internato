package br.com.internato.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "perfis")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")   // <-- mapeia para a coluna real
    private Long id;

    private String nome;
}

    

    
