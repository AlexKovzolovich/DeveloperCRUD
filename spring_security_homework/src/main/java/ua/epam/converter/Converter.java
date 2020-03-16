package ua.epam.converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface Converter<S, T> {
  T convert(S entity);

  S unConvert(T dto);

  default List<T> convertAll(Collection<S> sourceCollection) {
    return sourceCollection.stream()
        .map(this::convert)
        .collect(Collectors.toList());
  }

  default List<S> unConvertAll(Collection<T> from) {
    return from.stream()
        .map(this::unConvert)
        .collect(Collectors.toList());
  }
}
