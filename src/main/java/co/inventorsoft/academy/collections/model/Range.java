package co.inventorsoft.academy.collections.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

public class Range<T extends Comparable> implements Set<T> {
  private final   T start;
  private final T end;
  private Function<T, T> incrementFunction;

  private Range(T start, T end, Function<T, T> incrementFunction) {
    this.start = start;
    this.end = end;
    this.incrementFunction = incrementFunction;
  }

  public int size() {
    double precision = 0.1;
    if (start instanceof Double ) {
      return (int) Math.ceil((((Number) end).floatValue() - ((Number) start).floatValue()) / precision);
    } else if (start instanceof Float) {
      return (int) Math.ceil((((Number) end).floatValue() - ((Number) start).floatValue()) / precision);
    }
    {
      return (int) Math.ceil((((Number) end).intValue() - ((Number) start).intValue())+1);

    }
  }

  public boolean isEmpty() {
    return start.compareTo(end) == 0;
  }

  public boolean contains(Object o) {
    T element = (T) o;
    return element.compareTo(start) >= 0 && element.compareTo(end) <= 0;
  }

  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private T current = start;

      @Override
      public boolean hasNext() {
        return current.compareTo(end) <= 0;
      }

      @Override
      public T next() {
        T result = current;
        current = incrementFunction.apply(current);
        return result;
      }
    };
  }

  public Object[] toArray() {
    throw new UnsupportedOperationException("operation toArray  is not supported");
  }

  public <T1> T1[] toArray(T1[] a) {
    throw new UnsupportedOperationException("operation toArray  is not supported");
  }

  public boolean add(T t) {
    throw new UnsupportedOperationException("operation add  is not supported");
  }

  public boolean remove(Object o) {
    throw new UnsupportedOperationException("operation remove elements  is not supported");
  }

  public boolean containsAll(Collection<?> c) {
    throw new UnsupportedOperationException("removing elements  is not supported");
  }

  public boolean addAll(Collection<? extends T> c) {
    throw new UnsupportedOperationException("operation add  is not supported");
  }

  public boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException("operation retainAll  is not supported");
  }

  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException("operation removeAll  is not supported");
  }

  public void clear() {
    throw new UnsupportedOperationException("operation clear  is not supported");
  }

  public static Range<Integer> of(Integer start, Integer end) {
    return of(start, end, integer -> integer + 1);
  }

  public static Range<Double> of(Double start, Double end) {
    return of(Math.round(start * 10.0) / 10.0,
        Math.round(end * 10.0) / 10.0, aDouble -> aDouble + 0.1);
  }

  public static Range<Float> of(Float start, Float end) {
    return of(Math.round(start * 10.0f) / 10.0f,
        Math.round(end * 10.0f) / 10.0f, aFloat -> aFloat + 0.1f);
  }

  public static Range<Short> of(Short start, Short end) {
    return of(start, end, aShort -> (short) (aShort + 1));
  }

  public static Range<Byte> of(Byte start, Byte end) {
    return of(start, end, aByte -> (byte) (aByte + 1));
  }

  public static <T extends Comparable> Range<T> of(T start, T end, Function<T, T> increment) {
    return new Range<>(start,end, increment);
  }
}
