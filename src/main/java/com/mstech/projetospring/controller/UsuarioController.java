package com.mstech.projetospring.controller;
import com.mstech.projetospring.business.UsuarioService;
import com.mstech.projetospring.infrastructure.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuario));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> listaUsuario() {
        return usuarioService.listaUsuario();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario buscarUsuarioPorId(@PathVariable("id") Long id) {
        return usuarioService.buscarPorId(id)
                .orElseThrow(() -> new
                        ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario Não Cadastrado."));
    }

}
