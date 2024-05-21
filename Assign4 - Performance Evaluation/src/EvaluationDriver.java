/**
 * Assignment 4 for CS 1410
 * This program evaluates the linear and binary searching, along
 * with comparing performance difference between the selection sort
 * and the built-in java.util.Arrays.sort.
 *
 * @author James Dean Mathias
 */
public class EvaluationDriver {
    static final int MAX_VALUE = 1_000_000;
    static final int MAX_ARRAY_SIZE = 100_000;
    static final int ARRAY_SIZE_START = 20_000;
    static final int ARRAY_SIZE_INCREMENT = 20_000;
    static final int NUMBER_SEARCHES = 50_000;

    public static void main(String[] args) {

        demoLinearSearchUnsorted();
        demoLinearSearchSorted();
        demoBinarySearchSelectionSort();
        demoBinarySearchFastSort();
    }

    public static void demoLinearSearchUnsorted() {
        System.out.println("--- Linear Search Timing (unsorted) ---");

        for(int i=ARRAY_SIZE_START; i<=MAX_ARRAY_SIZE; i+=ARRAY_SIZE_INCREMENT) {
            int[] myArray = generateNumbers(i,MAX_VALUE);
            int timesValueWasFound=0;
            long startTime=System.currentTimeMillis();
            for(int j=0; j<NUMBER_SEARCHES; j++) {
                int searchValue = (int) (Math.random() * (1 + MAX_VALUE));
                boolean wasFound = linearSearch(myArray, searchValue);
                if(wasFound) {
                    timesValueWasFound++;
                }
            }
            long endTime=System.currentTimeMillis();
            System.out.printf("Number of items       : %d\n",i);
            System.out.printf("Times value was found : %d\n",timesValueWasFound);
            System.out.printf("Total search time     : %d ms\n\n",endTime-startTime);
        }

    }

    public static void demoLinearSearchSorted() {
        System.out.println("--- Linear Search Timing (Selection Sort) ---");

        for(int i=ARRAY_SIZE_START; i<=MAX_ARRAY_SIZE; i+=ARRAY_SIZE_INCREMENT) {
            int[] myArray = generateNumbers(i,MAX_VALUE);
            int timesValueWasFound=0;
            long startTime=System.currentTimeMillis();
            selectionSort(myArray);
            for(int j=0; j<NUMBER_SEARCHES; j++) {
                int searchValue = (int) (Math.random() * (1 + MAX_VALUE));
                boolean wasFound = linearSearch(myArray, searchValue);
                if(wasFound) {
                    timesValueWasFound++;
                }
            }
            long endTime=System.currentTimeMillis();
            System.out.printf("Number of items       : %d\n",i);
            System.out.printf("Times value was found : %d\n",timesValueWasFound);
            System.out.printf("Total search time     : %d ms\n\n",endTime-startTime);
        }

    }

    public static void demoBinarySearchSelectionSort() {
        System.out.println("--- Binary Search Timing (Selection Sort) ---");

        for(int i=ARRAY_SIZE_START; i<=MAX_ARRAY_SIZE; i+=ARRAY_SIZE_INCREMENT) {
            int[] myArray = generateNumbers(i,MAX_VALUE);
            int timesValueWasFound=0;
            long startTime=System.currentTimeMillis();
            selectionSort(myArray);
            for(int j=0; j<NUMBER_SEARCHES; j++) {
                int searchValue = (int) (Math.random() * (1 + MAX_VALUE));
                boolean wasFound = binarySearch(myArray, searchValue);
                if(wasFound) {
                    timesValueWasFound++;
                }
            }
            long endTime=System.currentTimeMillis();
            System.out.printf("Number of items       : %d\n",i);
            System.out.printf("Times value was found : %d\n",timesValueWasFound);
            System.out.printf("Total search time     : %d ms\n\n",endTime-startTime);
        }

    }

    public static void demoBinarySearchFastSort() {
        System.out.println("--- Binary Search Timing (Arrays.sort) ---");

        for(int i=ARRAY_SIZE_START; i<=MAX_ARRAY_SIZE; i+=ARRAY_SIZE_INCREMENT) {
            int[] myArray = generateNumbers(i,MAX_VALUE);
            int timesValueWasFound=0;
            long startTime=System.currentTimeMillis();
            java.util.Arrays.sort(myArray);
            for(int j=0; j<NUMBER_SEARCHES; j++) {
                int searchValue = (int) (Math.random() * (1 + MAX_VALUE));
                boolean wasFound = binarySearch(myArray, searchValue);
                if(wasFound) {
                    timesValueWasFound++;
                }
            }
            long endTime=System.currentTimeMillis();
            System.out.printf("Number of items       : %d\n",i);
            System.out.printf("Times value was found : %d\n",timesValueWasFound);
            System.out.printf("Total search time     : %d ms\n\n",endTime-startTime);
        }

    }

    public static int[] generateNumbers(int howMany, int maxValue) {
        if(howMany>0) {
            int[] a = new int[howMany];
            for (int i = 0; i < a.length; i++) {
                a[i] = (int) (Math.random() * (maxValue + 1));
            }
            return a;
        }
        return null;
    }

    public static boolean linearSearch(int[] data, int search) {
        if(data==null) {
            return false;
        }
        for(int i=0; i<data.length; i++) {
            if(data[i]==search) {
                return true;
            }
        }
        return false;
    }

    public static boolean binarySearch(int[] data, int search) {
        if(data==null) {
            return false;
        }
        int left = 0;
        int right = data.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (data[mid] == search)
                return true;

            if (data[mid] < search)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return false;
    }

    public static void selectionSort(int[] data) {
//        if(data==null) {
//            return false;
//        }
        int n = data.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = data[minIndex];
            data[minIndex] = data[i];
            data[i] = temp;
        }
    }

}
