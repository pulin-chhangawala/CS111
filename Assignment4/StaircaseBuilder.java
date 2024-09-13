/*
 * Write your program inside the main method to build
 * a staicase in a 2D array of characters according
 * to the assignment description
 *
 * To compile:
 *        javac StaircaseBuilder.java
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */
public class StaircaseBuilder {
    
    public static void main(String[] args) {

        int d = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);

        char[][] array = new char[d][d];
       
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                array[i][j] = ' ';
            }
        }
       
        for (int i = 0; i < d && n > 0; i++) {
          
            for (int j = d - 1; j >= d - i - 1 && n > 0; j--) {
                array[i][j] = 'X';
                n--; 
            }
        }

        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }

        System.out.println("Bricks remaining:" + n);
    }
}