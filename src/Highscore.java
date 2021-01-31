/**
 * Klasa przechowujaca wynik razem z przypisanym do niego nickiem
 */
class HighScore {
    String nickname;
    int score;

    /**
     * Konstruktor
     * @param nickname nazwa gracza
     * @param score wynik gracza
     */
    HighScore(String nickname, int score){
        this.nickname = nickname;
        this.score = score;
    }
}
