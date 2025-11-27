package ru.itk;


import java.util.HashMap;
import java.util.Map;

public class CountOfElementsExample {
    /**
     * A method to count how many times an elements is encountered in an array
     * @param array - an array to count elements in
     * @return {@link Map<T, Integer>} a map where keys are unique elements of the array and values - their frequency
     * @param <T> - any type
     */
    private static <T> Map<T, Integer> countOfElements(T[] array) {
        HashMap<T, Integer> countOfElements = new HashMap<T, Integer>();

        for (T element : array) {
            countOfElements.merge(element, 1, Integer::sum);
        }

        return countOfElements;
    }

    public static void main(String[] args) {

        Integer[] intArray = {1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 4, 5, 6, 7, 7, 8, 9, 10, 10};
        String[] strArray = {"hello", "world", "hello", "it's me", "hello", "- James May"};

        System.out.println(countOfElements(intArray));
        System.out.println(countOfElements(strArray));
    }
}