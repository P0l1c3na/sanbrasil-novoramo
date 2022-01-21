package br.ifgoiano.sanbrasil.novoramo.interfaces;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

public interface ISanBrasilService<T, I extends Serializable> {

    /**
     * Salva uma entidade
     *
     * @param entity - Classe de entidade a ser persistida no banco
     * @return Optional<T> - Objeto Optional retornado após realizar a operação de salvar
     * @since 01/01/2022
     */
    T save(@Valid T entity);

    /**
     * Exclui uma entidade por id
     *
     * @param id - ID da entidade a ser excluída
     * @since 01/01/2022
     */
    void deleteById(I id);

    /**
     * Método para retornar a lista de usuarios.
     *
     * @return Optional<List < T>> - Um Optional contendo a lista de usuários.
     * @since 01/01/2022
     */
    List<T> findAll();

    /**
     * Busca a entidade pelo id.
     *
     * @param id - Id da entidade a ser encontrada.
     * @return um Optional da entidade buscada.
     * @since 01/01/2022
     */
    T findById(I id);

}

