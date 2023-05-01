package com.adolfo.VMAPI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

	UsuarioRepository usuarioRepository;

	public CommandLineAppStartupRunner(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
    public void run(String... args) throws Exception {
		Usuario user1 = new Usuario();
		user1.setNome("adolfo1");
		user1.setSenha("123");
		user1.setEmail("a@gmail.com");

		usuarioRepository.save(user1);

		Usuario user2 = new Usuario();
		user2.setNome("adolfo2");
		user2.setSenha("123");
		user2.setEmail("a@gmail.com");

		usuarioRepository.save(user2);

		Usuario user5 = new Usuario();
		user5.setNome("adolfo5");
		user5.setSenha("123");
		user5.setEmail("a@gmail.com");

		usuarioRepository.save(user5);

		Usuario user3 = new Usuario();
		user3.setNome("adolfo3");
		user3.setSenha("123");
		user3.setEmail("a@gmail.com");

		usuarioRepository.save(user3);

		Usuario user4 = new Usuario();
		user4.setNome("adolfo4");
		user4.setSenha("123");
		user4.setEmail("a@gmail.com");

		usuarioRepository.save(user4);
    }
}
