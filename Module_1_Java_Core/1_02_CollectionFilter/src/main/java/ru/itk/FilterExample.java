package ru.itk;


import java.lang.reflect.Array;
import java.util.Arrays;

public class FilterExample {

    /**
     * The method which is used to return a new array filtered with {@link Filter} implementation.
     * @param array - an array to 'filter'
     * @param filterImplementation - an implementation of the {@link Filter} interface
     * @return a new, filtered array
     * @param <T> - any type
     */
    public static <T> T[] filter(T[] array, Filter<T> filterImplementation) {
        T[] filteredArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);

        for(int i = 0; i < array.length; ++i) {
            filteredArray[i] = filterImplementation.apply(array[i]);
        }

        return filteredArray;
    }

    public static void main(String[] args) {
        // simple example - we will print out squared values from the array

        Integer[] tens = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        System.out.println(Arrays.toString(filter(tens, x -> x * x)));
    }
}