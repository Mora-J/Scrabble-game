package ve.edu.ucab.models;

public class Game {
    private Jugador[] jugadores;
    private final BolsaFichas bolsaFichas;
    private final Board board;

    public Game() {
        jugadores = new Jugador[2];
        bolsaFichas = new BolsaFichas();
        board = new Board();
    }

    public Game(Jugador[] jugadores) {
        this.jugadores = jugadores;
        bolsaFichas = new BolsaFichas();
        board = new Board();
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
    }

    public Board getBoard() {
        return board;
    }

    public BolsaFichas getBolsaFichas() {
        return bolsaFichas;
    }
}
