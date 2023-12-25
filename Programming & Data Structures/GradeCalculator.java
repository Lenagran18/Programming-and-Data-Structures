import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = 0;
        int A = 0;
        int B = 0;
        int C = 0;
        int D = 0;
        int F = 0;

        while (true) {
            int input = Integer.valueOf(scanner.nextLine());

            if (input < 0) {
                break;
            }
            if (input != 0) {
                count = count + 1;

                if (input <= 59) {
                    F = F + 1;
                } else if (input <= 69) {
                    D = D + 1;
                } else if (input <= 79) {
                    C = C + 1;
                } else if (input <= 89) {
                    B = B + 1;
                } else {
                    A = A + 1;
                }
            }
        }
        System.out.println("Total number of grades = " + count);
        System.out.println("Number of A's = " + A);
        System.out.println("Number of B's = " + B);
        System.out.println("Number of C's = " + C);
        System.out.println("Number of D's = " + D);
        System.out.println("Number of F's = " + F);
    }
}