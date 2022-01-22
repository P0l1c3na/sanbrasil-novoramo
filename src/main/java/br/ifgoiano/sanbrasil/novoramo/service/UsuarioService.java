package br.ifgoiano.sanbrasil.novoramo.service;

import br.ifgoiano.sanbrasil.novoramo.enums.PerfilUsuario;
import br.ifgoiano.sanbrasil.novoramo.interfaces.ISanBrasilService;
import br.ifgoiano.sanbrasil.novoramo.model.Perfil;
import br.ifgoiano.sanbrasil.novoramo.model.Usuario;
import br.ifgoiano.sanbrasil.novoramo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class UsuarioService  implements ISanBrasilService<Usuario, String> {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository repository;

    /**
     * @param ativo - Indica se o usuario estará ativo ou não
     * */
    public Usuario createUser(String email, String senha, List<Perfil> perfis, Boolean ativo, String nome, String telefone) {
        return this.save(handleUser(email, senha, perfis, ativo, nome, telefone));
    }

    public Usuario handleUser(String email, String senha, List<Perfil> perfis, Boolean ativo, String nome, String telefone) {
        Usuario user = new Usuario();
        user.setAtivo(ativo);
        user.setDataDeCadastro(LocalDate.now());
        user.setEmail(email);
        user.setSenha(passwordEncoder.encode(senha));
        user.setPerfis(perfis);
        user.setNome(nome);
        user.setTelefone(telefone);
        return user;
    }

    public Usuario handleUser(Usuario usuario) {
        var user = new Usuario();
        user.setAtivo(Boolean.TRUE);
        user.setDataDeCadastro(LocalDate.now());
        user.setEmail(user.getEmail());
        user.setSenha(passwordEncoder.encode(usuario.getSenha()));
        user.setPerfis(Collections.singletonList(new Perfil(2L, PerfilUsuario.USUARIO.name())) );
        user.setNome(usuario.getNome());
        user.setTelefone(usuario.getTelefone());
        return user;
    }

    @Override
    public Usuario save(@Valid Usuario entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Override
    public Usuario findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Long count(){
        return repository.count();
    }

    public Usuario findByNome(String nome){
        return repository.findByNome(nome);
    }

    public Usuario salvarExterno(Usuario usuario){
        return this.save(this.handleUser(usuario));
    }
}
