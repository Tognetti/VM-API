package com.adolfo.VMAPI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Page<Usuario> findByNomeContaining(String nome, Pageable paging);

    Page<Usuario> findAll(Pageable paging);
}
