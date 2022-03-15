package org.anita.adventofcode.util;

import java.util.Objects;

public class Pair<T, T1> {
  private T key;
  private T1 value;

  public Pair(T key, T1 value) {
    this.key = key;
    this.value = value;
  }

  public T getKey() {
    return key;
  }
  public T1 getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Pair)) {
      return false;
    }
    Pair<?, ?> pair = (Pair<?, ?>) o;
    return Objects.equals(key, pair.key) && Objects.equals(value, pair.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, value);
  }
}
