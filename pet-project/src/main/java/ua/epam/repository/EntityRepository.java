package ua.epam.repository;

import java.util.List;

public interface EntityRepository<T, ID> {
  void save(T entity);
  T get(ID id);
  List<T> getAll();
  void delete(ID id);
}
