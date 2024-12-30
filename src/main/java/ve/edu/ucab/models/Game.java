package ve.edu.ucab.models;

public class Game {
    private Jugador[] jugadores;
    private final BolsaFichas bolsaFichas;
    private final Board board;
    private int turnoActual;
    private Jugador jugadorActual;

    public Game() {
        jugadores = new Jugador[2];
        for (int i = 0; i < jugadores.length; i++) {
            jugadores[i] = new Jugador();
        }
        bolsaFichas = new BolsaFichas();
        board = new Board();
        this.turnoActual = 0;
    }

    public Game(Jugador[] jugadores) {
        this.jugadores = jugadores;
        bolsaFichas = new BolsaFichas();
        board = new Board();
        this.turnoActual = 0;
    }

    public void actualizarAtrilJugadores() {
        for(Jugador j : jugadores) {
            j.rellenarFichas(bolsaFichas);
        }
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

    public int getTurnoActual() {
        return turnoActual;
    }

    public void setTurnoActual(int turnoActual) {
        this.turnoActual = turnoActual;
    }
}
