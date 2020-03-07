package ua.epam.mapper;

import ua.epam.exceptions.PersistException;
import ua.epam.model.BasicEntity;

import java.util.List;

public interface Mapper <T extends BasicEntity, R, V> {
    List<T> map(R r) throws PersistException;
    void map(T t, V v) throws PersistException;
}
