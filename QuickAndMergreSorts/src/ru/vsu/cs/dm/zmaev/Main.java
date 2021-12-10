package ru.vsu.cs.dm.zmaev;

import java.util.Arrays;

import java.util.Arrays;

public class Main {
    private static int quickSortCounter;
    private static int mergeSortCounter;
    private final static String dividerLine = "-------------------------------";

    public static double[] quickSort(double[] source){
        double[] arr = Arrays.copyOf(source, source.length);
        return quickSort(arr, 0, arr.length - 1);
    }
    private static double[] quickSort(double[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        quickSortCounter++;
        return arr;
    }
    private static int partition(double[] arr, int left, int right) {
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
            quickSortCounter++;
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }
    private static void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static double[] mergeSort(double[] source){
        double[] arr = Arrays.copyOf(source, source.length);
        if (arr.length < 2) {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2);
        double[] left = Arrays.copyOfRange(arr, 0, middle);
        double[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(mergeSort(left), mergeSort(right));
    }

    protected static double[] merge(double[] left, double[] right) {
        double[] result = new double[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
            mergeSortCounter++;
        }

        for (double num : left) {
            result[i++] = num;
        }

        for (double num : right) {
            result[i++] = num;
        }

        return result;
    }


    public static void main(String[] args) {
        double[] arr = new double[]{3, 62, 7, 78, -3, 5, 8, -1, 0, 7, 3, 0, 5, -110, 23};
        double[] worstForQuick = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        System.out.println(dividerLine);
        System.out.println();
        System.out.println("\t\tUnsorted array");
        testSorts(arr);
        System.out.println("\t\tSorted array");
        testSorts(worstForQuick);
    }

    public static void testSorts(double[] arr){
        System.out.println();
        System.out.println(dividerLine);
        System.out.println("Array with length = " + arr.length +":");
        System.out.println(Arrays.toString(arr));
        System.out.println();
        System.out.println(dividerLine);

        System.out.println("Result of Quick sort:");
        double[] quickResult = quickSort(arr);
        System.out.println(Arrays.toString(quickResult));
        System.out.println();
        System.out.println("Comparisons: " + quickSortCounter);
        System.out.println(dividerLine);
        System.out.println("Result of Merge sort: ");
        double[] mergeResult = mergeSort(arr);
        System.out.println(Arrays.toString(mergeResult));
        System.out.println();
        System.out.println("Comparisons: " + mergeSortCounter);
        System.out.println(dividerLine);
        System.out.println();
        quickSortCounter = 0;
        mergeSortCounter = 0;
    }
}
