package com.adepadua.forumhub.forumhub.model.entity;

import com.adepadua.forumhub.forumhub.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.nio.MappedByteBuffer;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "topicos")
@Getter
@Setter
@RequiredArgsConstructor
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "O titulo não pode ser vazio ou nulo.")
    @Size(min = 5, max = 20, message = "O titulo deve ter entre 5 e 20 caracteres.")
    private String titulo;

    @NotBlank(message = "A mensagem não pode ser nula ou vazia.")
    @Size(min = 10, max = 250, message = "A mensagem deve ter entre 10 e 250 caracteres.")
    private String mensagem;

    @NotNull
    private LocalDate dataCriacao = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private List<Resposta> respostas;

    @ManyToOne
    @JoinColumn(name = "autor")
    private Usuario autor;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    public Topico(String titulo, String mensagem, LocalDate dataCriacao, Status status, Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.dataCriacao = dataCriacao;
        this.status = status;
        this.autor = autor;
        this.curso = curso;
    }
}
