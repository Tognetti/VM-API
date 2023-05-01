package com.adolfo.VMAPI;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;

    public UsuarioService(UsuarioRepository usuarioRepository, EmailService emailService) {
        this.usuarioRepository = usuarioRepository;
        this.emailService = emailService;
    }

    @Transactional
    public Usuario criaUsuario(Usuario usuario) {
        Usuario novoUsuario = usuarioRepository.save(usuario);
        emailService.enviaEmail(usuario);
        return novoUsuario;
    }

    public Page<Usuario> getUsuarios(String nome, Pageable paging) {
        return (nome == null) ? usuarioRepository.findAll(paging) : usuarioRepository.findByNomeContaining(nome, paging);
    }

    public Optional<Usuario> getUsuario(Long id) {
        return usuarioRepository.findById(id);
    }
}
