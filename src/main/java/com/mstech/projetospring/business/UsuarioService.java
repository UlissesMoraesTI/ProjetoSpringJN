package com.mstech.projetospring.business;

import com.mstech.projetospring.infrastructure.entity.Usuario;
import com.mstech.projetospring.infrastructure.exceptions.ConflictException;
import com.mstech.projetospring.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(Usuario usuario) {
        try {
            emailExiste(usuario.getEmail());
            return usuarioRepository.save(usuario);
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado. " + e.getCause());
        }
    }

    public void emailExiste(String email){
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email já cadastrado. " + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado. " + e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public List<Usuario> listaUsuario() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }
}
