package com.adolfo.VMAPI;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*
Foram implementados testes para cada método do UsuarioService, considerando também
os diferentes cenários possíveis de cada método, como o getUsuarios, que pode
ser usado filtrando os usuários pelo nome ou não. Foram usados Mocks para as requisições
na base de dados.
*/

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    EmailService emailService;

    @InjectMocks
    UsuarioService usuarioService;

    Usuario usuario1 = new Usuario(1L,"Adolfo", "senha123", "teste@gmail.com");
    Usuario usuario2 = new Usuario(2L,"Augusto", "senha123", "teste@gmail.com");
    Usuario usuario3 = new Usuario(3L,"Vitor", "senha123", "teste@gmail.com");
    List<Usuario> usuarios = new ArrayList<>(Arrays.asList(usuario1, usuario2, usuario3));

    Pageable pagingParameters = PageRequest.of(0, 5);

    @Test
    void criaUsuario_deveRetornarNovoUsuario() {
        Usuario usuario = new Usuario("Adolfo", "senha123", "adolfo@gmail.com");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario result = usuarioService.criaUsuario(usuario);

        verify(usuarioRepository, times(1)).save(usuario);
        verify(emailService, times(1)).enviaEmail(usuario);
        assertEquals("Adolfo", result.getNome());
        assertEquals("senha123", result.getSenha());
        assertEquals("adolfo@gmail.com", result.getEmail());
    }

    @Test
    void getUsuarios_deveRetornarTodosUsuarios() {
        Page<Usuario> usuariosPage = new PageImpl<>(usuarios);

        when(usuarioRepository.findAll(pagingParameters)).thenReturn(usuariosPage);

        assertEquals(usuarioService.getUsuarios(null, pagingParameters).getContent().size(), 3);
        verify(usuarioRepository, times(1)).findAll(pagingParameters);
    }

    @Test
    void getUsuarios_deveRetornarUsuariosFiltrados() {
        Page<Usuario> usuariosPage = new PageImpl<>(List.of(usuario1));

        when(usuarioRepository.findByNomeContaining("Adolfo", pagingParameters)).thenReturn(usuariosPage);

        assertEquals(usuarioService.getUsuarios("Adolfo", pagingParameters).getContent().size(), 1);
        verify(usuarioRepository, times(1)).findByNomeContaining("Adolfo", pagingParameters);
    }

    @Test
    void getUsuario_deveRetornarUsuarioPeloId() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario1));

        Optional<Usuario> result = usuarioService.getUsuario(1L);

        assertEquals(result.get(), usuario1);
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void getUsuarios_naoDeveEncontrarUsuarioComIdInexistente() {
        when(usuarioRepository.findById(10L)).thenReturn(Optional.empty());

        Optional<Usuario> result = usuarioService.getUsuario(10L);

        assertTrue(result.isEmpty());
        verify(usuarioRepository, times(1)).findById(10L);
    }

}
