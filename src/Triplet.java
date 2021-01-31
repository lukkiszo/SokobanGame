/**
 * Klasa grupujaca dwie wspolrzedne
 * @param <A>
 * @param <B>
 */
public class Triplet<A, B, C> {
    public final A a;
    public final B b;
    public final C c;

    /**
     * Konstruktor
     * @param a1
     * @param b1
     * @param c1
     */
    public Triplet(A a1, B b1, C c1) {
        this.a = a1;
        this.b = b1;
        this.c = c1;
    }
}
