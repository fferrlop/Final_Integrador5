package OrganizacionQuicksort;

import java.util.ArrayList;
import java.util.List;
import static OrganizacionQuicksort.Utiles.less;
import static OrganizacionQuicksort.Utiles.swap;
import java.util.Arrays;


public class Quicksort {
    private List<String> steps = new ArrayList<>();

    public List<String> getSteps() {
        return steps;
    }

    public <T extends Comparable<T>> T[] sort(T[] array) {
        steps.add(Arrays.toString(array));
        doSort(array, 0, array.length - 1);
        return array;
    }

    private <T extends Comparable<T>> void doSort(T[] array, int left, int right) {
        if (left < right) {
            int pivot = randomPartition(array, left, right);
            steps.add(Arrays.toString(array));
            doSort(array, left, pivot - 1);
            doSort(array, pivot, right);
        }
    }

    private <T extends Comparable<T>> int randomPartition(T[] array, int left, int right) {
        int randomIndex = left + (int) (Math.random() * (right - left + 1));
        swap(array, randomIndex, right);
        return partition(array, left, right);
    }

    private <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        int mid = (left + right) >>> 1;
        T pivot = array[mid];

        while (left <= right) {
            while (less(array[left], pivot)) {
                ++left;
            }
            while (less(pivot, array[right])) {
                --right;
            }
            if (left <= right) {
                swap(array, left, right);
                ++left;
                --right;
            }
        }
        return left;
    }

    private <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    private <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}