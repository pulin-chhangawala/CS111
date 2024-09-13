public class randomBS{
    public static void main (String[] args) {
        int[] arr = {1,2,3,4,5};
        int sum=0;
        for (int i = 0; i<arr.length; i++){
            for (int loop = i;loop>0; loop--){
                sum+=arr[loop];
            }
        }
    }
}