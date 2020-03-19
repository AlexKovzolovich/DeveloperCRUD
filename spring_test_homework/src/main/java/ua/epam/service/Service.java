package ua.epam.service;

import java.util.List;

public interface Service<T, ID> {

  T getById(ID id);

  List<T> getAll();

  void save(T t);

  void delete(T t);

  void update(T t);
}
