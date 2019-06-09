import java.util.Arrays;

class MergeSort
{
    public static void merge(int [] arr, int p, int q, int r)
    {
        int lenL = q - p + 1;
        int lenR = r - q;

        int [] L = new int [lenL];
        int [] R = new int [lenR];

        int i, j, k = p;

        for (i = 0; i < lenL; i++)
            L[i] = arr[p + i];
        for (i = 0; i < lenR; i++)
            R[i] = arr[q + 1 + i];

        i = 0;
        j = 0;
        while (i < lenL && j < lenR) {
            if (L[i] < R[j])
                arr[k] = L[i++];
            else
                arr[k] = R[j++];
            k++;
        }

        while (i < lenL)
            arr[k++] = L[i++];
        while (j < lenR)
            arr[k++] = R[j++];
    }

    public static void merge_sort(int [] arr, int p, int r)
    {
        if (r - p >= 1) {
            int q = p + (r - p) / 2;

            merge_sort(arr, p, q);
            merge_sort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }
    public static void main(String [] args)
    {
        int [] arr = {3, 41, 52, 26, 38, 57, 9, 49};

        System.out.println("before sort : " + Arrays.toString(arr));
        merge_sort(arr, 0, arr.length - 1);
        System.out.println("after sort : " + Arrays.toString(arr));
    }
}
