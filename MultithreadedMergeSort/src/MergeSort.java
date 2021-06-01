class MergeSort {

    private void merge(int arr[], int l, int m, int r) {
    	
        /* Find sizes of two subarrays to be merged */
        int n1 = m - l + 1;
        int n2 = r - m;
 
        int L[] = new int[n1];
        int R[] = new int[n2];
 
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
 
        /* Merge the temp arrays */
        int i = 0, j = 0;
        int k = l;
        
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            }
            else {
                arr[k++] = R[j++];
            }
        }
 
        /* Copying remaining elements of L[] if any */
        while (i < n1) {
            arr[k++] = L[i++];
        }
 
        /* Copying remaining elements of R[] if any */
        while (j < n2) {
            arr[k++] = R[j++];
        }
    }
 
    public void sort(int arr[], int l, int r) {
        if (l < r) {

            int m = (l+r)/2;
 
            /* Sorting 1st and 2nd halves */
            sort(arr, l, m);
            sort(arr, m + 1, r);
 
            merge(arr, l, m, r);
        }
    }
}