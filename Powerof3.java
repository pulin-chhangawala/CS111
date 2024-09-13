public class Powerof3 {
    public static void printTable (int num){
        for (int i= 1; i<=num; i++){
            for (int j=1; j<= num; j++){
                System.out.print(i*j + " ");
            }
            System.out.println();
         }
        
}

    public static void main (String[] args){
        int n= Integer.parseInt(args[0]);
        printTable(n);

    }
}
