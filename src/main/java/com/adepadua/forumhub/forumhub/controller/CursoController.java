package com.adepadua.forumhub.forumhub.controller;

import com.adepadua.forumhub.forumhub.model.dto.CriarCursoDTO;
import com.adepadua.forumhub.forumhub.model.dto.CriarTopicoDTO;
import com.adepadua.forumhub.forumhub.model.entity.Curso;
import com.adepadua.forumhub.forumhub.repository.CursoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {
    private final CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<CriarCursoDTO> criarCurso(@Valid @RequestBody CriarCursoDTO dto) {
        Curso curso = dto.toEntity();
        cursoRepository.save(curso);

        return ResponseEntity.status(201).body(dto);
    }
}
