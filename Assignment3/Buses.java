/*
 *
 * Write the Buses program inside the main method
 * according to the assignment description.
 * 
 * To compile:
 *        javac Buses.java
 * To execute:
 *        java Buses 7302
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */

public class Buses {
    public static void main(String[] args) {

        // WRITE YOUR CODE HERE
        int number = Integer.parseInt(args[0]);
        if (number < 0) 
        {
            System.out.println("ERROR");
        }
         else 
         {
            int sum = 0;
            while (number > 0) 
            {
                sum += number % 10;
                number /= 10;
            }
            if (sum % 2 == 0) 
            {
                System.out.println("LX");
            } 
            else 
            {
                System.out.println("H");
            }
        }
    }
}
