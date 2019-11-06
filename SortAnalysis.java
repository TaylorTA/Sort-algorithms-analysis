

public class SortAnalysis {
    public static int breakpoint = 1;

    /*******************************************************************
     * main
     *
     * Purpose: Test sort algorithms
     *
     ******************************************************************/
    public static void main(String[] args) {
        System.out.println("Starting program - comp2140 a1\n");

        long[]ElapsedTimes = new long[100];
        int[]array = new int[10000];
        fillArray(array);
        double averageTime = 0;

        //choose breaking point
        chooseBreakingPoint(array);
        System.out.println("The best breaking point is: " + breakpoint);

        // test insertion sort
        for(int i=0;i<100;i++) {
            long start, stop, elapsedTime = 0;
            randomize(array, 5000);

            start = System.nanoTime();
            insertionSort(array);
            stop = System.nanoTime();
            elapsedTime = stop - start;

            ElapsedTimes[i] = elapsedTime;
            boolean test = checkArray(array);
            if (!test) {
                System.out.println("There is something wrong with insertion sort.");
            }
        }
        averageTime = arithmeticMean(ElapsedTimes);
        System.out.println("insertion sort's average time: " + averageTime);

        // test selection sort
        for(int i=0;i<100;i++) {
            long start, stop, elapsedTime = 0;
            randomize(array, 5000);

            start = System.nanoTime();
            selectionSort(array);
            stop = System.nanoTime();
            elapsedTime = stop - start;

            ElapsedTimes[i] = elapsedTime;
            boolean test = checkArray(array);
            if (!test) {
                System.out.println("There is something wrong with selection sort.");
            }
        }
        averageTime = arithmeticMean(ElapsedTimes);
        System.out.println("selection sort's average time: " + averageTime);

        // test merge sort
        for(int i=0;i<100;i++) {
            long start, stop, elapsedTime = 0;
            randomize(array, 5000);

            start = System.nanoTime();
            mergeSort(array);
            stop = System.nanoTime();
            elapsedTime = stop - start;

            ElapsedTimes[i] = elapsedTime;
            boolean test = checkArray(array);
            if (!test) {
                System.out.println("There is something wrong with merge sort.");
            }
        }
        averageTime = arithmeticMean(ElapsedTimes);
        System.out.println("merge sort's average time: " + averageTime);

        // test quick sort
        for(int i=0;i<100;i++) {
            long start, stop, elapsedTime = 0;
            randomize(array, 5000);

            start = System.nanoTime();
            quickSort(array);
            stop = System.nanoTime();
            elapsedTime = stop - start;

            ElapsedTimes[i] = elapsedTime;
            boolean test = checkArray(array);
            if (!test) {
                System.out.println("There is something wrong with quick sort.");
            }
        }
        averageTime = arithmeticMean(ElapsedTimes);
        System.out.println("quick sort's average time: " + averageTime);

        // test hybrid quick sort
        for(int i=0;i<100;i++) {
            long start, stop, elapsedTime = 0;
            randomize(array, 5000);

            start = System.nanoTime();
            hybridQuickSort(array);
            stop = System.nanoTime();
            elapsedTime = stop - start;

            ElapsedTimes[i] = elapsedTime;
            boolean test = checkArray(array);
            if (!test) {
                System.out.println("There is something wrong with hybrid quick sort.");
            }
        }
        averageTime = arithmeticMean(ElapsedTimes);
        System.out.println("hybrid quick sort's average time: " + averageTime);

        System.out.println("\nEnd of processing.");
    }


    /*******************************************************************
     * insertionSort
     *
     * Purpose: It sorts only positions array[start]
     * to (and including) array[end-1] in the array, not touching
     * any other position in array.
     *
     ******************************************************************/
    private static void insertionSort( int[] array, int start, int end) {
        int siftVal; // item to be sifted down
        int j; // in sifting, a[j] is compared to siftVal
        for (int i = start; i < end; i++) {
            // a[0] to a[i-1] is a sorted list.
            // Sift a[i] down to its correct location.
            siftVal = array[i];
            j = i - 1; // a[j+1] is the empty slot
            while (j >= start && array[j] > siftVal) {
                array[j + 1] = array[j]; // move an item right
                j--;
            } // end while
            array[j + 1] = siftVal;
        }// end for
    } // end insertSort

    /*******************************************************************
     * insertionSort
     *
     * Purpose: call the above private method, passing the correct values to
     * the private method's parameters so that it sorts the entire array.
     *
     ******************************************************************/
    public static void insertionSort(int[] array) {
        insertionSort(array, 0, array.length);
    }

    /*******************************************************************
     * findMin
     *
     * Purpose: to find the minimum item in the unsorted part of the array.
     *
     ******************************************************************/
    private static int findMin(int[] array, int start, int end) {
        int min = start;
        for (int i = start + 1; i < end; i++) {
            if (array[i] < array[min]) {
                min = i;
            }
        }
        return min;
    }

    /*******************************************************************
     * selectionSort
     *
     * Purpose: It sorts the whole array. At each step, it adds another
     * item to the sorted part and that sorted part is always at the left
     * end of the array
     *
     ******************************************************************/
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = findMin(array, i, array.length);
            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }

    /*******************************************************************
     * mergeSort
     *
     * Purpose: It simply calls the private recursive method with the
     * array and the extra parameters.
     *
     ******************************************************************/
    public static void mergeSort(int[] array){
        int[] temp = new int[array.length];
        mergeSort(array, 0,array.length, temp);

    }

    /*******************************************************************
     * mergeSort
     *
     * Purpose: It does the recursive merge sort. It receives the
     * array, indices start and end, and the temporary array as parameters.
     *
     ******************************************************************/
    private static void mergeSort(int[] array, int start, int end, int[] temp) {
        int mid;
        // New base case
        if(end - start == 2){
            if (array[start] > array[end-1]) {
                swap(array,start,end-1);
            }
            return;
        }
        // recursive step
        if (end - start > 2){
            // Recursive case: if > 1 item
            mid = start + (end - start) / 2;
            mergeSort(array, start, mid, temp);
            mergeSort(array, mid, end, temp);
            merge(array, start, mid, end, temp);
        }
    }

    /*******************************************************************
     * merge
     *
     * Purpose: It merges two sorted sublists (dened by three indices
     * start, mid, and end) into one sorted list, using the extra array temp.
     *
     ******************************************************************/
    private static void merge(int[]array, int start, int mid, int end, int[]temp){
        int currL = start; // index of current item in left half
        int currR = mid; // index of current item in right half
        int currT; // index in temp
        for ( currT = start; currT < end; currT++ ) {
            if (currL < mid && (currR >= end || array[currL] < array[currR])) { // copy from left half if that value is smaller
                // or if no values remain in the right half
                temp[currT] = array[currL];
                currL++;
            } else // copy from the right half
            {
                temp[currT] = array[currR];
                currR++;
            }
        }
        // end for
        // Copy the merged items into the original array.
        for ( currT = start; currT < end; currT++ )
            array[currT] = temp[currT];
    }

    /*******************************************************************
     * quickSort
     *
     * Purpose: It simply calls the private recursive method, passing it
     * the extra parameters.
     *
     ******************************************************************/
    public static void quickSort( int[] array ) {
        quickSort(array, 0, array.length-1);
    }

    /*******************************************************************
     * quickSort
     *
     * Purpose: It is the recursive quick sort method, which
     * receives the array, and indices start and end as parameters.
     *
     ******************************************************************/
    private static void quickSort(int array[], int start, int end) {
        // new base case
        if(end - start ==2) {
            if (array[start] > array[end-1]) {
                swap(array,start,end-1);
            }
        }
        // recursive step
        if (start < end) {
            int pivot = partition(array, start, end);

            quickSort(array, start, pivot-1);
            quickSort(array, pivot+1, end);
        }
    }

    /*******************************************************************
     * medianOfThree
     *
     * Purpose: It chooses a pivot from the items in positions
     * start to end-1 (inclusive) in the array using the
     * median-of-three method, and swaps the chosen
     * pivot into position start in the array.
     *
     ******************************************************************/
    public static int medianOfThree(int[] array, int end,int start){
        int mid = (end+start)/2;

        if(array[end] > array[mid])
            swap(array,end,mid);

        if(array[end] > array[start])
            swap(array,end, start);

        if(array[mid] > array[start])
            swap(array,mid, start);

        swap(array,mid, start);

        return array[start];
    }

    /*******************************************************************
     * partition
     *
     * Purpose: It partitions the items in positions start to end-1
     * (inclusive) in the array using the chosen pivot.
     *
     ******************************************************************/
    private static int partition(int array[], int begin, int end) {
        int pivot = medianOfThree(array, begin, end);
        int i = (begin-1);
        for (int j = begin; j < end; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array,i,j);
            }
        }
        swap(array,i+1,end);
        return i+1;
    }

    /*******************************************************************
     * hybridQuickSort
     *
     * Purpose: If array[start] to (and including) array[end-1] consists
     * of at least BREAKPOINT items, then do the usual quick sort steps
     * (choose a pivot using the median-of-three technique, partition the
     * items using the chosen pivot, and  recursively call hybrid QuckSort
     * twice to sort each of the smalls and the bigs.
     *
     ******************************************************************/
    private static void hybridQuickSort( int[] array, int start, int end ){
        // new base case
        if(end - start ==2) {
            if (array[start] > array[end-1]) {
                swap(array,start,end-1);
            }
        }
        if (end - start > breakpoint) {
            int pivot = partition(array, start, end);
            quickSort(array, start, pivot-1);
            quickSort(array, pivot+1, end);
        }
        else{
            insertionSort(array,start,end);
        }
    }

    /*******************************************************************
     * hybridQuickSort
     *
     * Purpose: Its task is to simply call the above private hybridQuickSort
     * method, passing the correct values to the private method's parameters
     * so that it sorts the entire array.
     *
     ******************************************************************/
    public static void hybridQuickSort ( int[] array ){
        hybridQuickSort(array,0,array.length-1);
    }

    /*******************************************************************
     * isInOrder
     *
     * Purpose: It should be passed an array and it
     * must check that each item is no bigger than the next item in the array.
     *
     ******************************************************************/
    public static boolean checkArray(int[] array){
        for(int i=0; i< array.length-1; i++){
            if(array[i]>array[i+1]) {
                return false;
            }
        }
        return true;
    }

    /*******************************************************************
     * fillArray
     *
     * Purpose: It should be passed an array array.
     * It should ll the array with the numbers 0 to array.length-1, in order.
     *
     ******************************************************************/
    public static void fillArray(int[] array){
        for(int i=0; i<array.length;i++)
            array[i] = i;
    }

    /*******************************************************************
     * randomize
     *
     * Purpose: It should be passed an array
     * array and a number n. It should perform n random swaps in the array.
     *
     ******************************************************************/
    public static void randomize(int[] array, int n){
        for(int i=0; i<n; i++){
            int index1 = (int)(Math.random()*array.length);
            int index2 = (int)(Math.random()*array.length);
            swap(array,index1,index2);
        }
    }

    /*******************************************************************
     * swap
     *
     * Purpose: It swap the values between index1 and index2
     *
     ******************************************************************/
    public static void swap(int[]array, int index1,int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    /*******************************************************************
     * chooseBreakingPoint
     *
     * Purpose: This method
     * should nd a good value for hybridQuickSort to use as its BREAKPOINT.
     *
     ******************************************************************/
    public static void chooseBreakingPoint(int[] testArray){
        int[] breakPoints = {25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100};
        int best = breakPoints[0];
        double bestTime = Double.MAX_VALUE;
        for(int j=0; j<breakPoints.length; j++){
            breakpoint = breakPoints[j];
            long[] eTime = new long[100];
            for(int i=0;i<100;i++) {
                long start,stop,elapsedTime;
                randomize(testArray, 5000);

                start = System.nanoTime();
                hybridQuickSort(testArray);
                stop = System.nanoTime();
                elapsedTime = stop - start;

                eTime[i] = elapsedTime;
            }
            if(arithmeticMean(eTime)<bestTime){
                best = breakpoint;
                bestTime = arithmeticMean(eTime);
            }

        }
        breakpoint = best;
    }

    /*******************************************************************
     * arithmeticMean
     *
     * Purpose: Compute the average of long values.
     * To avoid long overflow, use type double in the computation.
     *
     ******************************************************************/
    public static double arithmeticMean( long data[] ) {
        double sum = 0;
        for (int i = 0; i < data.length; i++)
            sum += (double)data[i];
        return sum / (double)data.length;
    } // end arithmeticMean
}
