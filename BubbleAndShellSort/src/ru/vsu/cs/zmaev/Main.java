package ru.vsu.cs.zmaev;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static int bubbleSortCounter = 0;
    public static int shellSortCounter = 0;

    public static int[] bubbleSort(int[] arr) {
        int[] resArr = Arrays.copyOf(arr, arr.length);
        boolean isSorted = false;
        int temp;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < resArr.length - 1; i++) {
                if(resArr[i] > resArr[i + 1]) {
                    bubbleSortCounter++;
                    isSorted = false;

                    temp = resArr[i];
                    resArr[i] = resArr[i + 1];
                    resArr[i + 1] = temp;
                }
            }
        }
        return resArr;
    }

    public static int[] shellSort(int[] arr) {
        int[] resArr = Arrays.copyOf(arr, arr.length);
        int i, j, step;
        int temp;
        for (step = resArr.length / 2; step > 0; step /= 2) {
            for (i = step; i < resArr.length; i++) {
                temp = resArr[i];
                for (j = i; j >= step; j -= step) {
                    if (temp < resArr[j - step]) {
                        shellSortCounter++;
                        resArr[j] = resArr[j - step];
                    } else {
                        break;
                    }
                }
                resArr[j] = temp;
            }
        }
        return resArr;
    }

    public static int[] fillArrWithRandomNumbers(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) Math.round((Math.random() * 30) - 15);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] unsortedArrWorst = new int[] {5, 4, 3, 2, 1, 0};
        int[] unsortedArrBest = new int[] {0, 1, 2, 3, 4, 5};
        int[] randomArray = fillArrWithRandomNumbers(6);
        System.out.print("Unsorted worst-time array: ");
        System.out.println(Arrays.toString(unsortedArrWorst));
        System.out.print("Unsorted best-time array: ");
        System.out.println(Arrays.toString(unsortedArrBest));
        System.out.println("-----------------------Bubble Sort-----------------------");
        System.out.print("Sorted: ");
        int[] bubbleSortWorst = bubbleSort(unsortedArrWorst);
        System.out.println(Arrays.toString(bubbleSortWorst));
        System.out.println("Bubble sort worst time: " + bubbleSortCounter);
        bubbleSortCounter = 0;
        int[] bubbleSortBest = bubbleSort(unsortedArrBest);
        System.out.println("Bubble sort best time " + bubbleSortCounter);
        System.out.println("-----------------------Shell Sort-----------------------");
        System.out.print("Sorted: ");
        int[] shellSortWorst = shellSort(unsortedArrWorst);
        System.out.println(Arrays.toString(shellSortWorst));
        System.out.println("Shell sort worst time: " + shellSortCounter);
        shellSortCounter = 0;
        int[] shellSortBest = bubbleSort(unsortedArrBest);
        System.out.println("Shell sort best time " + bubbleSortCounter);
        System.out.println("-----------------------Random Array-----------------------");
        bubbleSortCounter = 0;
        shellSortCounter = 0;
        System.out.print("Unsorted randomly filled array: ");
        System.out.println(Arrays.toString(randomArray));
        int[] sortedRandomArrayBubble = bubbleSort(randomArray);
        System.out.println(Arrays.toString(sortedRandomArrayBubble));
        System.out.println("Bubble sort time: " + bubbleSortCounter);
        int[] sortedRandomArrayShel = shellSort(randomArray);
        System.out.println("Shell sort time: " + shellSortCounter);
    }
}
