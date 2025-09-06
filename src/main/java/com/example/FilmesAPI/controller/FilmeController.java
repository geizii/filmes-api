package com.example.FilmesAPI.controller;

import com.example.FilmesAPI.model.Filme;
import com.example.FilmesAPI.service.FilmeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
@CrossOrigin(origins = "*")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    // GET - Listar todos os filmes
    @GetMapping
    public ResponseEntity<List<Filme>> listarTodos() {
        List<Filme> filmes = filmeService.listarTodos();
        return ResponseEntity.ok(filmes);
    }

    // GET - Buscar filme por ID
    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPorId(@PathVariable Long id) {
        try {
            Filme filme = filmeService.buscarPorId(id);
            return ResponseEntity.ok(filme);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST - Criar novo filme
    @PostMapping
    public ResponseEntity<Filme> criarFilme(@Valid @RequestBody Filme filme) {
        try {
            Filme novoFilme = filmeService.salvar(filme);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoFilme);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT - Atualizar filme completo
    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable Long id, @Valid @RequestBody Filme filmeAtualizado) {
        try {
            Filme filme = filmeService.atualizar(id, filmeAtualizado);
            return ResponseEntity.ok(filme);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PATCH - Atualizar parcialmente
    @PatchMapping("/{id}")
    public ResponseEntity<Filme> atualizarParcial(@PathVariable Long id, @RequestBody Filme filmeAtualizado) {
        try {
            Filme filme = filmeService.atualizarParcial(id, filmeAtualizado);
            return ResponseEntity.ok(filme);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // DELETE - Remover filme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable Long id) {
        try {
            filmeService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Buscar por título
    @GetMapping("/buscar/titulo")
    public ResponseEntity<List<Filme>> buscarPorTitulo(@RequestParam String titulo) {
        List<Filme> filmes = filmeService.buscarPorTitulo(titulo);
        return ResponseEntity.ok(filmes);
    }

    // GET - Buscar por diretor
    @GetMapping("/buscar/diretor")
    public ResponseEntity<List<Filme>> buscarPorDiretor(@RequestParam String diretor) {
        List<Filme> filmes = filmeService.buscarPorDiretor(diretor);
        return ResponseEntity.ok(filmes);
    }

    // GET - Buscar por gênero
    @GetMapping("/buscar/genero")
    public ResponseEntity<List<Filme>> buscarPorGenero(@RequestParam String genero) {
        List<Filme> filmes = filmeService.buscarPorGenero(genero);
        return ResponseEntity.ok(filmes);
    }

    // GET - Buscar por nota mínima
    @GetMapping("/buscar/nota")
    public ResponseEntity<List<Filme>> buscarPorNota(@RequestParam Integer nota) {
        List<Filme> filmes = filmeService.buscarPorNota(nota);
        return ResponseEntity.ok(filmes);
    }

    // GET - Listar ordenado por nota
    @GetMapping("/top")
    public ResponseEntity<List<Filme>> listarPorNota() {
        List<Filme> filmes = filmeService.listarPorNota();
        return ResponseEntity.ok(filmes);
    }
}