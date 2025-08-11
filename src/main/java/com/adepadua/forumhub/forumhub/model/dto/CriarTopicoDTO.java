package com.adepadua.forumhub.forumhub.model.dto;

import com.adepadua.forumhub.forumhub.model.entity.Curso;
import com.adepadua.forumhub.forumhub.model.entity.Topico;
import com.adepadua.forumhub.forumhub.model.entity.Usuario;
import com.adepadua.forumhub.forumhub.model.enums.Status;

import java.time.LocalDate;


public record CriarTopicoDTO(String titulo, String mensagem, String nomeAutor, Long cursoId) {

    public CriarTopicoDTO(String titulo, String mensagem, String nomeAutor, Long cursoId) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.nomeAutor = nomeAutor;
        this.cursoId = cursoId;
    }
}
