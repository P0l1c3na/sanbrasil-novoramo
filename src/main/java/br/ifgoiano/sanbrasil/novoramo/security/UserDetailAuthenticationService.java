package br.ifgoiano.sanbrasil.novoramo.security;

import br.ifgoiano.sanbrasil.novoramo.model.Usuario;
import br.ifgoiano.sanbrasil.novoramo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class UserDetailAuthenticationService implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = usuarioService.findById(username);
        if (Objects.nonNull(user) && Objects.nonNull(user.getEmail())) {
            return new User(user.getEmail(), user.getSenha(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Usuario não encotrado ou senha incorretos: " + username);
        }
    }
}
