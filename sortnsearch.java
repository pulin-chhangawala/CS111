public class sortnsearch {
    public int binarySearch(int[] arr, int x){
        int first = 0;
    int last = arr.length - 1;
    int mid = (first + last) / 2;
    while (first <= last) {
        if (arr[mid] < x) {
            first = mid + 1;
        } else if (arr[mid] == x) {
            return mid;
        } else {
            last = mid - 1;
        }
        mid = (first + last) / 2;
    }
    return -1;
    }
}
