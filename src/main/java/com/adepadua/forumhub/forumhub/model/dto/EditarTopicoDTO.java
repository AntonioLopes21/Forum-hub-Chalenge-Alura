package com.adepadua.forumhub.forumhub.model.dto;

import com.adepadua.forumhub.forumhub.model.entity.Curso;
import com.adepadua.forumhub.forumhub.model.entity.Usuario;
import com.adepadua.forumhub.forumhub.model.enums.Status;

import java.time.LocalDate;

public record EditarTopicoDTO(String titulo, String mensagem) {
}
