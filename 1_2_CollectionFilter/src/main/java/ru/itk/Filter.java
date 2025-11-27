package ru.itk;

/**
 * An interface which has the apply method
 * @param <T> - any type
 */
@FunctionalInterface
public interface Filter<T> {
    T apply(T o);
}
