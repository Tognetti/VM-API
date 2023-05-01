package com.adolfo.VMAPI;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void enviaEmail(Usuario usuario) {
        System.out.println("Simulando envio de e-mail para: " + usuario.getEmail());
    }
}
