package com.adepadua.forumhub.forumhub.controller;

import com.adepadua.forumhub.forumhub.model.dto.CriarTopicoDTO;
import com.adepadua.forumhub.forumhub.model.dto.ExibirTopicoDTO;
import com.adepadua.forumhub.forumhub.model.entity.Curso;
import com.adepadua.forumhub.forumhub.model.entity.Topico;
import com.adepadua.forumhub.forumhub.model.entity.Usuario;
import com.adepadua.forumhub.forumhub.repository.CursoRepository;
import com.adepadua.forumhub.forumhub.repository.TopicoRepository;
import com.adepadua.forumhub.forumhub.repository.UsuarioRepository;
import com.adepadua.forumhub.forumhub.service.TopicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {
    private final TopicoService topicoService;

    @GetMapping
    public ResponseEntity<List<ExibirTopicoDTO>> listarTopico() {
        return ResponseEntity.status(HttpStatus.OK).body(topicoService.listarTopicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExibirTopicoDTO> litarTopicoPorId(@PathVariable Long id) {
        ExibirTopicoDTO dto = topicoService.exibirTopicoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    public ResponseEntity<CriarTopicoDTO> criarTopico(@Valid @RequestBody CriarTopicoDTO dto) {
        CriarTopicoDTO topico = topicoService.criarTopico(dto);
        return ResponseEntity.status(201).body(topico);
    }

   @PutMapping("/{id}")
   public ResponseEntity<ExibirTopicoDTO> editarTopico(@PathVariable Long id ,@Valid @RequestBody CriarTopicoDTO dto) {
        topicoService.editarTopico(id, dto);
   }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTopico(@PathVariable Long id) {
        return topicoService.excluirTopicoPeloId(id);
    }
}
