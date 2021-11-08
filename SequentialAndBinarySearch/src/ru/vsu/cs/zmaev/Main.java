package ru.vsu.cs.zmaev;

import java.util.Arrays;

public class Main {

    public static int[] binarySearch(int[] arr, int key) {
        int counter = 0;
        int[] result = new int[2];
        int first = 0;
        int last = arr.length - 1;
        while (first <= last) {
            counter++;
            int curr = (first + last) / 2;
            if (key == arr[curr]) {
                result[0] = curr;
                result[1] = counter;
                break;
            } else if (key < arr[curr]) {
                last = curr - 1;
            } else {
                first = curr + 1;
            }
        }
        return result;
    }

    public static int[] sequentialSearch(int[] arr, double key) {
        int count = 0;
        int[] result = new int[2];
        for (int i = 0; i < arr.length; i++ ) {
            count++;
            if (arr[i] == key) {
                result[0] = i;
                result[1] = count;
                return result;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        System.out.println("Array: " + Arrays.toString(arr));
        int ifFirst = arr[0];
        int ifMiddle = arr[arr.length / 2];
        int ifLast = arr[arr.length - 1];
        int[] firstBinary = binarySearch(arr, ifFirst);
        int[] middleBinary = binarySearch(arr, ifMiddle);
        int[] lastBinary = binarySearch(arr, ifLast);
        int[] firstSequential = sequentialSearch(arr, ifFirst);
        int[] middleSequential = sequentialSearch(arr, ifMiddle);
        int[] lastSequential = sequentialSearch(arr, ifLast);
        System.out.println("----------------Binary Search----------------");
        System.out.printf("If element is first: Searched element under the number %s was found after %s comparisons.\n", firstBinary[0], firstBinary[1]);
        System.out.printf("If element is middle: Searched element under the number %s was found after %s comparisons.\n", middleBinary[0], middleBinary[1]);
        System.out.printf("If element is last: Searched element under the number %s was found after %s comparisons.\n", lastBinary[0], lastBinary[1]);
        if (firstSequential == null || middleSequential == null || lastSequential == null) {
            System.out.println("No such element in the array.");
            return;
        }
        System.out.println("----------------Sequential Search----------------");
        System.out.printf("If element is first: Searched element under the number %s was found after %s comparisons.\n", firstSequential[0], firstSequential[1]);
        System.out.printf("If element is middle: Searched element under the number %s was found after %s comparisons.\n", middleSequential[0], middleSequential[1]);
        System.out.printf("If element is last: Searched element under the number %s was found after %s comparisons.\n", lastSequential[0], lastSequential[1]);
    }
}