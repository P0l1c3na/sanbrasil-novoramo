package br.ifgoiano.sanbrasil.novoramo.service;

import br.ifgoiano.sanbrasil.novoramo.enums.PerfilUsuario;
import br.ifgoiano.sanbrasil.novoramo.interfaces.ISanBrasilService;
import br.ifgoiano.sanbrasil.novoramo.model.Perfil;
import br.ifgoiano.sanbrasil.novoramo.model.Usuario;
import br.ifgoiano.sanbrasil.novoramo.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

@Service
@Slf4j
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

    public boolean validarSenha(StringBuilder stb, String senha, String confirmacaoSenha) {
        if (Objects.isNull(senha)
                || Objects.isNull(confirmacaoSenha)
                || senha.isEmpty()
                || confirmacaoSenha.isEmpty()) {
            stb.append("A Senha e Confirmação de Senha devem ser preenchidos! \n");
            return false;
        } else {
            if (!confirmacaoSenha.equals(senha)) {
                stb.append("A Senha e a Confirmação de Senha devem ser iguais! \n");
                return false;
            }
            if (!Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,}")
                    .matcher(senha)
                    .matches()) {
                stb.append("A Senha deve conter pelo menos 8 caracteres, 1 Letra Maiúscula, 1 Número, 1 Símbolo e não deve ter sequencia!\n");
                return false;
            }
        }
        return true;
    }

    public void updateSenha(String senha, String email) {
        repository.updateSenha(this.passwordEncoder.encode(senha), email);
    }
}
