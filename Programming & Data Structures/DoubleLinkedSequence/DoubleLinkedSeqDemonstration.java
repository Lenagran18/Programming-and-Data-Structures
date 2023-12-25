package DoubleLinkedSequence;
public class DoubleLinkedSeqDemonstration {
    public static void main(String[] args) {
        DoubleLinkedSeq s = new DoubleLinkedSeq();
        for (double element = 1.0; element < 11.0; element += 1.0)
            s.addAfter(element);
        System.out.println("sequence s");
        s.print();

        DoubleLinkedSeq t = s.clone();
        System.out.println("sequence t after clone from s");
        t.print();
        t.addAfter(-1.0);
        t.addAfter(-2.0);

        t.start();
        for (int i = 0; i < 4; i++)
            t.advance();
        t.addAfter(11.0);
        t.addAfter(12.0);
        System.out.println("sequence t after addAfter");
        t.print();

        DoubleLinkedSeq u = t.clone();
        System.out.println("sequence u after clone from t");
        u.print();
        u.start();
        for (int i = 0; i < 5; i++)
            u.advance();
        u.removeCurrent();
        u.removeCurrent();
        System.out.println("sequence u after removeCurrent");
        u.print();

        DoubleLinkedSeq v = s.clone();
        System.out.println("sequence v after clone from s");
        v.print();
        v.start();
        v.addBefore(-1.0);
        v.addBefore(-2.0);
        for (int i = 0; i < 6; i++)
            v.advance();
        v.addBefore(11.0);
        v.addBefore(12.0);
        System.out.println("sequence v after addBefore");
        v.print();
        v.addAll(s);
        System.out.println("sequence v after addAll(s)");
        v.print();

        DoubleLinkedSeq w = DoubleLinkedSeq.catenation(s, t);
        System.out.println("sequence w = s + t");
        w.print();

        DoubleLinkedSeq x = new DoubleLinkedSeq();
        System.out.println("sequence x");
        x.print();
        System.out.printf("%5.2f", x.getCurrent());
    }
}