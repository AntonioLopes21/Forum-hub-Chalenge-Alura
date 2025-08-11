package com.adepadua.forumhub.forumhub.controller;

import com.adepadua.forumhub.forumhub.model.dto.CriarTopicoDTO;
import com.adepadua.forumhub.forumhub.model.entity.Topico;
import com.adepadua.forumhub.forumhub.repository.TopicoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {


    @PostMapping
    public ResponseEntity<CriarTopicoDTO> criarTopico(@Valid @RequestBody CriarTopicoDTO dto) {
        Topico novoTopico = new Topico(dto.)
    }
}
