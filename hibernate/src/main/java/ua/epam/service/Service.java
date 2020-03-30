package ua.epam.service;

import java.util.List;

public interface Service<T, ID> {

  T getById(ID id);

  List<T> getAll();

  T create(T t);

  void delete(T t);

  T update(T t);
}
