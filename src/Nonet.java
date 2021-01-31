/**
 * Klasa przechowujaca zbior danych
 * @param <A>
 * @param <B>
 * @param <C>
 * @param <D>
 * @param <E>
 * @param <F>
 * @param <G>
 * @param <H>
 * @param <I>
 */
public class Nonet<A, B, C, D, E, F, G, H, I>{
    public final A a;
    public final B b;
    public final C c;
    public final D d;
    public final E e;
    public final F f;
    public final G g;
    public final H h;
    public final I i;

    /**
     * Konstruktor
     * @param a1
     * @param b1
     * @param c1
     * @param d1
     * @param e1
     * @param f1
     * @param g1
     * @param h1
     * @param i1
     */
    public Nonet(A a1, B b1, C c1, D d1, E e1, F f1, G g1, H h1, I i1) {
        this.a = a1;
        this.b = b1;
        this.c = c1;
        this.d = d1;
        this.e = e1;
        this.f = f1;
        this.g = g1;
        this.h = h1;
        this.i = i1;
    }
}