package com.nativeblood.clientes.service;
import com.nativeblood.clientes.exception.UsuarioCadastradoExeption;
import com.nativeblood.clientes.model.entity.Usuario;
import com.nativeblood.clientes.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario salvar(Usuario usuario) {
        boolean exists = repository.existsByUsername(usuario.getUsername());
        if(exists){
            throw  new UsuarioCadastradoExeption((usuario.getUsername()));
        }
        return repository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username ) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado!"));

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")
                .build();
    }
}
