package edu.neu.coe.info6205.sort.elementary;

import java.util.Random;

public class SortMainMethod {
    @SuppressWarnings("rawtypes")
	public static void main(String[] args) {
        Integer[] randomArray, orderedArray, partiallyOrderedArray, reverseOrderedArray;
        InsertionSort insertionSort = new InsertionSort();
        int n = 2500; // starting value for n
        int numTrials = 6; // number of trials for each value of n
        for (int i = 0; i < numTrials; i++) {
            System.out.println("n = " + n);
            randomArray = createRandomArray(n);
            orderedArray = createOrderedArray(n);
            partiallyOrderedArray = createPartiallyOrderedArray(n);
            reverseOrderedArray = createReverseOrderedArray(n);

            System.out.println("Random array:");
            measureTime(insertionSort, randomArray);
            System.out.println("Ordered array:");
            measureTime(insertionSort, orderedArray);
            System.out.println("Partially ordered array:");
            measureTime(insertionSort, partiallyOrderedArray);
            System.out.println("Reverse ordered array:");
            measureTime(insertionSort, reverseOrderedArray);

            n *= 2; // double n for next trial
            System.out.println();
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	private static void measureTime(InsertionSort insertionSort, Integer[] array) {
        long startTime = System.currentTimeMillis();
       
        insertionSort.sort(array, 0, array.length);
       
        long endTime = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (endTime - startTime) + "ms");
       
    }

    private static Integer[] createRandomArray(int n) {
        Random random = new Random();
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    private static Integer[] createOrderedArray(int n) {
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }
        return array;
    }

    private static Integer[] createPartiallyOrderedArray(int n) {
        Integer[] array = new Integer[n];
        for (int i = 0; i < n / 2; i++) {
            array[i] = i;
        }
        for (int i = n / 2; i < n; i++) {
            array[i] = n - i - 1;
        }
        return array;
    }

    private static Integer[] createReverseOrderedArray(int n) {
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = n - i - 1;
        }
        return array;
    }
}