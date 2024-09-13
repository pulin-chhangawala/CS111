import java.util.Scanner;

public class loan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the principal amount: ");
        double principal = scanner.nextDouble();

        System.out.print("Enter the annual interest rate (in decimal): ");
        double rate = scanner.nextDouble();

        System.out.print("Enter the number of years: ");
        int years = scanner.nextInt();

        double monthlyRate = rate / 12.0;
        int months = years * 12;

        double monthlyPayment = (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
        System.out.println("\nMonthly Payments: " + monthlyPayment);

        System.out.println("\nMonth\t\tInterest\tPrincipal\tBalance");
        for (int i = 1; i <= months; i++) {
            double interest = principal * monthlyRate;
            double principalPaid = monthlyPayment - interest;
            principal = principal - principalPaid;

            System.out.println(i + "\t\t" + interest + "\t\t" + principalPaid + "\t\t" + principal);
        }

        scanner.close();
    }
}

