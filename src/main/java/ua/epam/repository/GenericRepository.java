package ua.epam.repository;

import ua.epam.exceptions.PersistException;
import ua.epam.model.BasicEntity;

import java.util.List;

public interface GenericRepository<T extends BasicEntity, ID> {
    T getById(ID id) throws PersistException;
    List<T> getAll() throws PersistException;
    void save(T t) throws PersistException;
    void delete(T t) throws PersistException;
    void update(T t) throws PersistException;

}
