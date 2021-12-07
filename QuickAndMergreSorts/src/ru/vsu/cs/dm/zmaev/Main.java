package ru.vsu.cs.dm.zmaev;

import java.util.Arrays;

public class Main {

    private static int quickSortCounter = 0;
    private static int mergeSortCounter = 0;



    public static int[] mergeSort(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);

        int tempLength = arr.length / 2;
        int tempLength2 = arr.length - tempLength;
        int i = 0, j = 0;
        int[] arrFirstPart = new int[tempLength];
        for (int f = 0; f < tempLength; f++) {
            arrFirstPart[i] = arr[i];
        }

        int[] arrSecondPart = new int[tempLength2];
        for (int f = tempLength; f < tempLength2; f++) {
            arrSecondPart[i] = arr[i];
        }

        for (int k = 0; k < arr.length; k++) {
            if (i > arrFirstPart.length - 1) {
                mergeSortCounter++;
                int a = arrSecondPart[j];
                arr[k] = a;
                j++;
            } else if (j > arrSecondPart.length - 1) {
                mergeSortCounter++;
                int a = arrFirstPart[i];
                arr[k] = a;
                i++;
            } else if (arrFirstPart[i] < arrSecondPart[j]) {
                mergeSortCounter++;
                int a = arrFirstPart[i];
                arr[k] = a;
                i++;
            } else {
                mergeSortCounter++;
                int b = arrSecondPart[j];
                arr[k] = b;
                j++;
            }
        }
        return arr;
    }

    public static int[] quickSort(int[] arr, int low, int high) {
        int[] array = Arrays.copyOf(arr, arr.length);

        if (array.length == 0 || low >= high) {
            return null;
        }

        int middle = low + (high - low) / 2;
        int pivot = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (array[i] < pivot) {
                quickSortCounter++;
                i++;
            }

            while (array[j] > pivot) {
                quickSortCounter++;
                j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j) {
            quickSort(array, low, j);
        }

        if (high > i) {
            quickSort(array, i, high);
        }

        return array;
    }

    public static void main(String[] args) {
        int[] sortedArr = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int[] unsortedArr = new int[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

        System.out.println("-----------------Unsorted Array-----------------");
        System.out.println(Arrays.toString(unsortedArr));
        System.out.println("-----------------Sorted Array-----------------");
        System.out.printf("QuickSort: %s\n", Arrays.toString(quickSort(unsortedArr, 0, 10)));
//        System.out.printf("MergeSort: %s\n", Arrays.toString(mergeSort(unsortedArr)));
        System.out.println("-----------------Quick sort time-----------------");
        System.out.printf("Quick sort unsorted time: %s\n", quickSortCounter);
        quickSortCounter = 0;
        quickSort(sortedArr, 0, 10);
        System.out.printf("Quick sort sorted time: %s\n", quickSortCounter);
        mergeSort(unsortedArr);
        System.out.println("-----------------Merge sort time-----------------");
        System.out.printf("Merge sort unsorted time: %s\n", mergeSortCounter);
        mergeSortCounter = 0;
        mergeSort(sortedArr);
        System.out.printf("Merge sort sorted time: %s\n", mergeSortCounter);
    }
}

