package br.com.internato.domain;

import br.com.internato.domain.Perfil;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha; // armazenar hash BCrypt

   @ManyToOne
   @JoinColumn(name = "id_perfil")   // INTEGER no banco
   private Perfil perfil;


   // public enum Perfil {
     //   COORDENADOR, PRECEPTOR, ALUNO, SECRETARIA
    //}
}