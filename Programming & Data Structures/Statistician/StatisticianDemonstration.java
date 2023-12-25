package Statistician;

public class StatisticianDemonstration {
    public static void main(String[] args) {
        Statistician s = new Statistician();
        System.out.println("Supply the following numbers to s:");
        for (double i = 0; i < 10; i++) {
            s.next(i);
            System.out.print(i + " ");
        }
        System.out.println("\n\nThe information of statistician s is");
        print(s);

        Statistician t = new Statistician();
        System.out.println("Supply the following numbers to t:");
        for (double i = 0; i < 2.5; i += 0.5) {
            t.next(i);
            System.out.print(i + " ");
        }
        for (double i = 9; i > 6.5; i -= 0.5) {
            t.next(i);
            System.out.print(i + " ");
        }
        System.out.println("\n\nThe information of statistician t is");
        print(t);

        if (s.equals(t))
            System.out.println("s equals to t\n");

        t.clear();
        System.out.println("The information of statistician t after t.clear() is");
        print(t);

        System.out.println("Supply the following numbers to t:");
        for (double i = 0; i < 20; i++) {
            t.next((i + 1) * 0.5);
            System.out.print((i + 1) * 0.5 + " ");
        }
        System.out.println("\n\nThe information of statistician t is");
        print(t);

        Statistician u;
        u = Statistician.union(s, t);
        System.out.println("The information of statistician u=s+t is");
        print(u);

        if (!s.equals(u))
            System.out.println("s does not equal to u\n");

        s.addAll(t);
        System.out.println("The information of statistician s after s.addAll(t) is");
        print(s);

        s.clear();
        System.out.println("The information of statistician s after s.clear() is");
        print(s);
    }

    private static void print(Statistician s) {
        System.out.println("  length = " + s.length());
        System.out.println("  sum = " + s.sum());
        System.out.println("  mean = " + s.mean());
        System.out.println("  minimum = " + s.minimum());
        System.out.println("  maximum = " + s.maximum() + "\n");
    }
}