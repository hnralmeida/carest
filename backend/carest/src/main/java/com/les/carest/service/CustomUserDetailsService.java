package com.les.carest.service;

import com.les.carest.model.Usuario;
import com.les.carest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Indica que esta classe é um bean gerenciado pelo Spring
public abstract class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository; // Repositório para buscar usuários no banco de dados

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            return new User(
                        usuario.getUsername(),
                        usuario.getPassword(),
                    List.of(new SimpleGrantedAuthority(null))
                );
        }
        throw new RuntimeException("0 fodas");
    }
}