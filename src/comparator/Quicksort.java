package comparator;
 
/*
 *
 * @author Sesh Venugopal. New Jersey. Jan 18, 2013.
 *
 */
public class Quicksort {
 
    private static <T extends Comparable<T>> int split(T[] list, int lo, int hi) {
        int left=lo+1;
        int right=hi;
        T pivot = list[lo];
         
        while (true) {
            while (left <= right) {
                if (list[left].compareTo(pivot) < 0) {
                    left++;
                } else {  break;
                }
            }
 
            while(right > left) {
                if (list[right].compareTo(pivot) < 0) {
                    break;
                } else {    right--;
                }
            }
 
            if (left >= right) {
                break; }
 
            // swap left and right items
            T temp = list[left];
            list[left] = list[right];
            list[right] = temp;
            //advance each one step
            left++; right--;
        }
         
        // swap pivot with left-1 position
        list[lo] = list[left-1];
        list[left-1] = pivot;
        // return the split point
         
        return left-1;
    }
     
    private static <T extends Comparable<T>> void sort(T[] list, int lo, int hi) {
        if ((hi-lo) <= 0) { // fewer than 2 items
            return; }
        
        int splitPoint = split(list,lo,hi);
        sort(list,lo,splitPoint-1);  // left subarray recursion
        sort(list,splitPoint+1,hi);  // right subarray recursion
    }
     
    public static <T extends Comparable<T>> void sort(T[] list) {
        if (list.length <= 1) {
            return;
        }
        sort(list,0,list.length-1);
    }
}