package com.mstech.projetospring.controller;
import com.mstech.projetospring.business.UsuarioService;
import com.mstech.projetospring.controller.dtos.UsuarioDTO;
import com.mstech.projetospring.infrastructure.entity.Usuario;
import com.mstech.projetospring.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuario));
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(), usuarioDTO.getSenha())

        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> listaUsuario() {
        return usuarioService.listaUsuario();
    }

    @GetMapping("/nome/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> listaPorNome(@PathVariable("nome") String nome) {
        return usuarioService.listaPorNome(nome);
    }

    @GetMapping("email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario buscarUsuarioPorEmail(@PathVariable("email") String email) {
        return usuarioService.buscarUsuarioPorEmail(email);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario buscarUsuarioPorId(@PathVariable("id") Long id) {
        return usuarioService.buscarPorId(id)
                .orElseThrow(() -> new
                        ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário Não Cadastrado."));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerUsuario(@PathVariable("id") Long id) {
        usuarioService.buscarPorId(id)
                .map(usuario -> {
                    usuarioService.removerPorId(usuario.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new
                        ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário Não Cadastrado."));
    }

    @DeleteMapping("/email/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> removerUsuarioPorEmail(@PathVariable String email){
        usuarioService.removerPorEmail(email);
        return ResponseEntity.ok().build();
    }
}
