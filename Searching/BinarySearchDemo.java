package Searching;

public class BinarySearchDemo {

    public static void main(String[] args) {
        int arr[] = {2,3,4,77,124,133,142, 222, 423};
        System.out.println(binarySearch(arr, 131));
    }

    public static int binarySearch(int arr[], int num){
        int low = 0;
        int high = arr.length-1;
        int mid   = low+ (high-low)/2;

        while(low<=high){

            if(arr[mid]==num){
                return mid;
            }
            if(arr[mid] > num){
                high = mid-1;
            }
            else{
                low = mid + 1;
            }

            mid   = low+ (high-low)/2;
        }
        return arr[mid];
    }
}
