import java.util.Arrays;

public class Main {
    private static int quickSortCounter = 0;
    private static int mergeSortCounter = 0;

    private final static String dividerLine = "-------------------------------";

    public static double[] quickSort(double[] source){
        double[] arr = Arrays.copyOf(source, source.length);
        return quickSort(arr, 0, arr.length - 1);
    }
    private static double[] quickSort(double[] arr, int left, int right) {
        if (left < right) {
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

            quickSort(arr, left, index - 1);
            quickSort(arr, index, right);
        }

        return arr;
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
            mergeSortCounter++;
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

    public static double[] fillArrayRnd(int size) {
        double[] arr = new double[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) Math.round((Math.random() * 30) - 15);
        }
        return arr;
    }

    public static void main(String[] args) {
        double[] arrRnd = fillArrayRnd(16);
        double[] worstForQuick = new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        double[] backSorted = new double[] {16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(dividerLine);
        System.out.println();
        System.out.println("\tRandom-filled array");
        showResult(arrRnd);
        System.out.println("\t\tSorted array");
        showResult(worstForQuick);
        System.out.println("\t\tBack-sorted array");
        showResult(backSorted);
    }

    public static void showResult(double[] array){
        double[] arr = Arrays.copyOf(array, array.length);
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
