package ua.epam.repository;

import java.util.List;

public interface Repository<T, ID> {

  void save(T entity);

  T get(ID id);

  List<T> getAll();

  void delete(ID id);
}
