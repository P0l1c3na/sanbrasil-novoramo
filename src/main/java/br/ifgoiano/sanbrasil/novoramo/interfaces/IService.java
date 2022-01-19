package br.ifgoiano.sanbrasil.novoramo.interfaces;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

public interface IService<T, I extends Serializable> {

    /**
     * Salva uma entidade
     *
     * @param entity - Classe de entidade a ser persistida no banco
     * @return Optional<T> - Objeto Optional retornado após realizar a operação de salvar
     * @since 03/03/2020
     */
    T save(@Valid T entity);

    /**
     * Exclui uma entidade
     *
     * @param id - ID da entidade a ser excluída
     * @since 03/03/2020
     */
    void delete(I id);

    /**
     * Método para retornar a lista de usuarios.
     *
     * @return Optional<List < T>> - Um Optional contendo a lista de usuários.
     * @since 03/03/2020.
     */
    List<T> findAll();

    /**
     * Busca a entidade pelo id.
     *
     * @param id - Id da entidade a ser encontrada.
     * @return um Optional da entidade buscada.
     * @since 03/03/2020.
     */
    T findById(I id);

}

