package com.nativeblood.clientes.rest;

import com.nativeblood.clientes.exception.UsuarioCadastradoExeption;
import com.nativeblood.clientes.model.entity.Usuario;
import com.nativeblood.clientes.model.repository.UsuarioRepository;
import com.nativeblood.clientes.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Usuario usuario) {
        try {
            service.salvar(usuario);
        }catch (UsuarioCadastradoExeption e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
