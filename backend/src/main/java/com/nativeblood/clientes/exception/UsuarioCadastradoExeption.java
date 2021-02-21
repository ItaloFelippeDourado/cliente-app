package com.nativeblood.clientes.exception;

public class UsuarioCadastradoExeption extends RuntimeException {

    public UsuarioCadastradoExeption(String login) {
        super("usuário já cadastrado para o login "+ login);

    }
}
