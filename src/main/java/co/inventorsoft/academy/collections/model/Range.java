package co.inventorsoft.academy.collections.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

public class Range<T> implements Set<T> {
    private final HashSet<T> rangeSet;

    private Range() {
        this.rangeSet = new HashSet<>();
    }

    public int size() {
        return rangeSet.size();
    }

    public boolean isEmpty() {
        return rangeSet.isEmpty();
    }

    public boolean contains(Object o) {
        return rangeSet.contains(o);
    }

    public Iterator<T> iterator() {
        return rangeSet.iterator();
    }

    public Object[] toArray() {
        return rangeSet.toArray();
    }

    public <T1> T1[] toArray(T1[] a) {
        return rangeSet.toArray(a);
    }

    public boolean add(T t) {
        return rangeSet.add(t);
    }

    public boolean remove(Object o) {
        return rangeSet.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return rangeSet.containsAll(c);
    }

    public boolean addAll(Collection<? extends T> c) {
        return rangeSet.addAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return rangeSet.retainAll(c);
    }

    public boolean removeAll(Collection<?> c) {
        return rangeSet.retainAll(c);
    }

    public void clear() {
        rangeSet.clear();
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
        Range<T> range = new Range<>();
        if (start.compareTo(end) == 0) {
            return range;
        }

        T tempNumber = start;
        while (tempNumber.compareTo(end) <= 0) {
            range.add(tempNumber);
            tempNumber = increment.apply(tempNumber);
        }
        return range;
    }
}
