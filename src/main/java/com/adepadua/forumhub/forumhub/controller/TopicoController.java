package com.adepadua.forumhub.forumhub.controller;

import com.adepadua.forumhub.forumhub.model.dto.CriarTopicoDTO;
import com.adepadua.forumhub.forumhub.model.entity.Curso;
import com.adepadua.forumhub.forumhub.model.entity.Topico;
import com.adepadua.forumhub.forumhub.model.entity.Usuario;
import com.adepadua.forumhub.forumhub.repository.CursoRepository;
import com.adepadua.forumhub.forumhub.repository.TopicoRepository;
import com.adepadua.forumhub.forumhub.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<CriarTopicoDTO> criarTopico(@Valid @RequestBody CriarTopicoDTO dto) {
        Usuario autor = usuarioRepository.findByNome(dto.nomeAutor())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        Curso curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Topico novoTopico = new Topico(dto.titulo(), dto.mensagem(), autor, curso);
        topicoRepository.save(novoTopico);

        return ResponseEntity.status(201).body(novoTopico.toDTO());
    }
}
