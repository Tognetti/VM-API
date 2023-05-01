package com.adolfo.VMAPI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuarios")
    public Usuario criaUsuario(@RequestBody Usuario usuario) {
        return usuarioService.criaUsuario(usuario);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> getUsuarios(@RequestParam(required = false) String nome,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Usuario> pageUsuarios = usuarioService.getUsuarios(nome, paging);

        Map<String, Object> response = new HashMap<>();
        response.put("usuarios", pageUsuarios.getContent());
        response.put("currentPage", pageUsuarios.getNumber());
        response.put("totalItems", pageUsuarios.getTotalElements());
        response.put("totalPages", pageUsuarios.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuario(id);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
