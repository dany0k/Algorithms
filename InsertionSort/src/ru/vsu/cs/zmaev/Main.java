package ru.vsu.cs.zmaev;

import java.util.Arrays;

public class Main {

    private static int binaryCounter = 0;
    private static int seqCounter = 0;

    public static int[] sortUsingBinarySearch(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);
        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            int low = 0;
            int mid;
            int high = i;

            while (low < high) {
                binaryCounter++;
                mid = low + (high - low) / 2;
                if (key < arr[mid]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            for (int j = i; j > low; j--) {
                arr[j] = arr[j - 1];
            }
            arr[low] = key;
        }
        return arr;
    }

    public static int[] sortUsingSequentialSearch(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);
        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                seqCounter++;
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    public static void showArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i == arr.length - 1 ? arr[i] + "]" : arr[i] + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {7, 6, 5, 4, 3, 2, 1};
        System.out.print("Array before sorting: ");
        showArray(arr);
        System.out.print("Array after sorting using binary search: ");
        int[] sortedBinary = sortUsingBinarySearch(arr);
        showArray(sortedBinary);
        System.out.println(binaryCounter + " iterations.");

        System.out.print("Array after sorting using sequential search: ");
        int[] sortedSeq = sortUsingSequentialSearch(arr);
        showArray(sortedSeq);
        System.out.println(seqCounter + " iterations.");
    }
}
