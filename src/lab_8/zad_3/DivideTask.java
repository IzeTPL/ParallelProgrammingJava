package lab_8.zad_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

class DivideTask extends RecursiveTask<int[]> {

    int[] arrayToDivide;

    public DivideTask(int[] arrayToDivide) {
        this.arrayToDivide = arrayToDivide;
    }

    protected int[] compute() {

        if (arrayToDivide.length > 1) {
            List<int[]> partitionedArray = partitionArray();


            DivideTask task1 = new DivideTask(partitionedArray.get(0));
            DivideTask task2 = new DivideTask(partitionedArray.get(1));

            task1.fork(); // lub: invokeAll(task1, task2); - invokeAll combines fork and join
            task2.fork(); // invokeAll can return a list of Futures with results


            //Wait for results from both tasks
            int[] array1 = task1.join();
            int[] array2 = task2.join();

            //Initialize a merged array
            int[] mergedArray = new int[array1.length + array2.length];

            mergeArrays(array1, array2, mergedArray);
            return mergedArray;


        }

        return arrayToDivide;
    }

    private void mergeArrays(
            int[] array1,
            int[] array2,
            int[] mergeArrays) {

        int i = 0, j = 0, k = 0;

        while ((i < array1.length) && (j < array2.length)) {

            if (array1[i] < array2[j]) {
                mergeArrays[k] = array1[i++];
            } else {
                mergeArrays[k] = array2[j++];
            }

            k++;
        }

        if (i == array1.length) {

            for (int a = j; a < array2.length; a++) {
                mergeArrays[k++] = array2[a];
            }

        } else {

            for (int a = i; a < array1.length; a++) {
                mergeArrays[k++] = array1[a];
            }

        }
    }

    public List<int[]> partitionArray() {

        int[] array1 = Arrays.copyOfRange(arrayToDivide, 0 , arrayToDivide.length/2);
        int[] array2 = Arrays.copyOfRange(arrayToDivide, arrayToDivide.length/2, arrayToDivide.length);

        List<int[]> partitionedArray = new ArrayList<>();
        partitionedArray.add(array1);
        partitionedArray.add(array2);

        return partitionedArray;
    }

}