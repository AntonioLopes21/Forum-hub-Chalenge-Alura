package com.adepadua.forumhub.forumhub.model.dto;

import com.adepadua.forumhub.forumhub.model.entity.Curso;
import com.adepadua.forumhub.forumhub.model.entity.Topico;


public record CriarTopicoDTO(String titulo, String mensagem, String nomeAutor, Curso curso) {

    public CriarTopicoDTO(String titulo, String mensagem, String nomeAutor, Curso curso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.nomeAutor = nomeAutor;
        this.curso = curso;
    }
}
