package com.adepadua.forumhub.forumhub.service;

import com.adepadua.forumhub.forumhub.model.dto.CriarCursoDTO;
import com.adepadua.forumhub.forumhub.model.entity.Curso;
import com.adepadua.forumhub.forumhub.repository.CursoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoService {
    private final CursoRepository cursoRepository;

    //post
    public CriarCursoDTO criarCurso(@Valid CriarCursoDTO dto) {
        cursoRepository.save(dto.toEntity());
        return dto;
    }
}
