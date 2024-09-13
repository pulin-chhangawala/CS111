public class staircase {
    public static void main(String[] args) {
        int d = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        char[][] stairs = new char[d][d];

        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                stairs[i][j] = ' ';
            }
        }

        for (int i = 0; i <stairs.length; i++) {
            for (int j = 0; j <= i && b > 0; j++) {
                stairs[i][j] = 'X';
                b--;
            }
        }

        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                System.out.print(stairs[i][j]);
            }
            System.out.println();
        }

        System.out.println("Bricks remaining: " + b);
    }
}