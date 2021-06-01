import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort extends RecursiveAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Decides when to fork or compute directly:
    private static final int SORT_THRESHOLD = 128;
 
    private final int[] arr;
    private final int l;
    private final int r;
 
    public ParallelMergeSort(int[] arr) {
        this(arr, 0, arr.length-1);
    }
 
    public ParallelMergeSort(int[] arr, int l, int r) {
        this.arr = arr;
        this.l = l;
        this.r = r;
    }
 
    public void sort() {
        compute();
    }
 
    @Override
    protected void compute() {
        if (l < r) {
            int size = r - l;
            if (size < SORT_THRESHOLD) {
                insertionSort();
            } else {
                int mid = l + Math.floorDiv(size, 2);
                invokeAll(
                        new ParallelMergeSort(arr, l, mid),
                        new ParallelMergeSort(arr, mid + 1, r));
                merge(mid);
            }
        }
    }
 
    private void insertionSort() {
        for (int i = l+1; i <= r; ++i) {
            int current = arr[i];
            int j = i-1;
            
            while (l <= j && current < arr[j]) {
                arr[j+1] = arr[j--];
            }
            arr[j+1] = current;
        }
    }
 
    private void merge(int mid) {
    	
    	/*Copy data to temp arrays*/
        int[] L = Arrays.copyOfRange(arr, l, mid+1);
        int[] R = Arrays.copyOfRange(arr, mid+1, r+1);
        
        /* Merge the temp arrays */
        int i = 0, j = 0;
        int k = l;
        
        while (i < L.length && j < R.length) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
 
        /* Copying remaining elements of L[] if any */
        while (i < L.length) {
            arr[k++] = L[i++];
        }
 
        /* Copying remaining elements of R[] if any */
        while (j < R.length) {
            arr[k++] = R[j++];
        }
    }
}
