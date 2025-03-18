package com.les.carest.service;

import com.les.carest.exception.RegistroNotFoundException;
import com.les.carest.exception.RegistroNotUpdated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class _GenericService<TipoEntidade, RepositorioGenerics extends JpaRepository<TipoEntidade, UUID>>
        implements _GenericServiceTypes<TipoEntidade> {

    protected RepositorioGenerics repositoryGenerics;

    protected _GenericService(RepositorioGenerics repositoryGenerics) {
        this.repositoryGenerics = repositoryGenerics;
    }

    @Override
    public List<TipoEntidade> listar() {
        try {
            return repositoryGenerics.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar entidades", e);
        }
    }

    @Override
    public TipoEntidade buscarPorId(UUID id) {
        try {
            Optional<TipoEntidade> optionalEntity = repositoryGenerics.findById(id);
            return optionalEntity.orElseThrow(() -> new RegistroNotFoundException(id));
        } catch (RegistroNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar entidade por ID", e);
        }
    }

    @Override
    public TipoEntidade criar(TipoEntidade entity) {
        try {
            return repositoryGenerics.save(entity);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao criar entidade", e);
        }
    }

    @Override
    public void excluir(UUID id) {
        try {
            Optional<TipoEntidade> optionalEntity = repositoryGenerics.findById(id);
            TipoEntidade entity = optionalEntity.orElseThrow(() -> new RegistroNotFoundException(id));
            repositoryGenerics.delete(entity);
        } catch (RegistroNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir entidade", e);
        }
    }

    @Override
    public TipoEntidade atualizar(UUID id, TipoEntidade entityAtualizada) {
        try {
            Optional<TipoEntidade> optionalEntity = repositoryGenerics.findById(id);
            TipoEntidade entity = optionalEntity.orElseThrow(() -> new RegistroNotUpdated(id));
            entity = entityAtualizada;
            return repositoryGenerics.save(entity);
        } catch (RegistroNotUpdated e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar entidade", e);
        }
    }
}