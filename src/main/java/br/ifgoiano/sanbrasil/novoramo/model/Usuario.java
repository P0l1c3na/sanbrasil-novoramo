package br.ifgoiano.sanbrasil.novoramo.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.List;

@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @Email
    @Length(max = 128)
    @Getter @Setter
    @Column(unique = true)
    private String email;


    @Setter
    @Getter
    private String senha;

    @Setter @Getter
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, targetEntity = Perfil.class)
    @JoinTable(name="perfil_usuario",
            joinColumns=@JoinColumn(name="usuario_email"),
            inverseJoinColumns=@JoinColumn(name="perfil_id")
    )
    @Getter @Setter
    private List<Perfil> perfis;

    @Getter @Setter
    private Boolean ativo;

    @Getter @Setter
    private LocalDate dataDeCadastro;

    @Getter @Setter
    private String telefone;

    public Usuario(@Email @Length(max = 128) String email) {
        this.email = email;
    }
}
